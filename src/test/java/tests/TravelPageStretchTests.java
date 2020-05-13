package tests;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import utils.DateUtils;
import utils.PropertyUtils;

import java.util.Comparator;

public class TravelPageStretchTests extends BaseTest {

	SoftAssertions softly = new SoftAssertions();

	@Test(description = "Verify combination of results filter and sort regards to annual trip selection")
	public void searchTravel_annualTripFiltersCombination_ShouldShowCorrectResults() {
		searchTravelByDefault();

		// Select 'Show All' option
		travelPage.getTravelFilters()
				.discardFilterTip()
				.showAll();

		// Select 'annual trip' option
		travelPage.getTravelDetailsFilter().selectAnnualTrip();

		// Select 'two persons' option
		travelPage.getTravelDetailsFilter().selectFourPersons();

		// Change start date and end date
		travelPage.getTravelDetailsFilter().changeTravelStartDate("20");

		// Change destination
		travelPage.getTravelDetailsFilter().changeDestination("Asia");

		// Filter by random available insurer
		String filteredInsurer = travelPage.getTravelFilters().getRandomInsuranceName();
		travelPage.getTravelFilters().filterByInsuranceName(filteredInsurer);

		// Sort price by low to high
		travelPage.getTravelSort().sortByPriceLowToHigh();

		// Change medical expenses filter range
		travelPage.getTravelFilters().seeMoreFilters()
				.changeMedicalExpensesFilter(1000000, 8000000);

		travelPage.getTravelCardDetails().waitForResultsLoad();

		// Assertion
		softly.assertThat(travelPage.getTravelCardDetails()
				.getAllInsurancesName())
				.contains(filteredInsurer);

		travelPage.getTravelCardDetails().getAllMedicalExpenses().forEach(number -> {
			softly.assertThat(number).isBetween(1000000, 8000000);
		});

		String currentStartDateFormatted = DateUtils.convertDateToString("20-05-2020",
				"dd-MM-yyyy", "dd MMM yyyy");
		softly.assertThat(travelPage.getTravelDestinationHeader()).contains(currentStartDateFormatted);

		String destination = travelPage.getTravelDetailsFilter().getCurrentSelectedDestination();
		softly.assertThat(destination).isEqualTo("Asia");
		softly.assertThat(travelPage.getTravelDestinationHeader()).contains("travel to " + destination);

		softly.assertThat(travelPage.getTravelCardDetails().getAllPrices())
				.isSortedAccordingTo(Comparator.comparingInt(o -> o));
		softly.assertAll();
	}

	private void searchTravelByDefault() {
		browserKeywords.goTo(PropertyUtils.getInstance().getHomePageUrl());
		homePage.searchTravelByDefaultOptions();
		travelPage.getTravelCardDetails().waitForResultsLoad();
	}
}
