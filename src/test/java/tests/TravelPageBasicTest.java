package tests;

import helper.StringConstants;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import utils.DateUtils;
import utils.PropertyUtils;
import utils.ServicesUtils;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class TravelPageBasicTest extends BaseTest {

	SoftAssertions softly = new SoftAssertions();
	Response response;

	@Test(description = "Verify at least 3 item cards are displayed using search default options")
	public void searchTravelByDefaultOptions_shouldDisplayAtLeast3Cards() {
		searchTravelByDefault();
		assertThat(travelPage.getNumberOfDisplayedCards()).isGreaterThanOrEqualTo(3);
	}

	@Test(description = "Verify promotion only results filter should show no results if there are no promos")
	public void searchTravelByDefaultOptions_promosOnly_shouldDisplayCorrectResults() {
		searchTravelByDefault();
		travelPage.getTravelFilters().showPromosOnly();
		response = ServicesUtils.execute(ServicesUtils.getCurrentTravelEndpoint(driver.getCurrentUrl()),
				ServicesUtils.HttpMethod.GET, true);
		int promosOnlyCardsCount = (int) response.jsonPath().getList(StringConstants.PROMOTION_ID_JSONPATH)
				.stream()
				.filter(promo -> !promo.toString().equals("00000000-0000-0000-0000-000000000000")).count();
		if (promosOnlyCardsCount > 0)
			assertThat(travelPage.getNumberOfDisplayedCards()).isEqualTo(promosOnlyCardsCount);
		else
			assertThat(travelPage.getNoResultsText()).contains("No plans match your filter criteria…");
	}

	@Test(description = "Filter - Verify results are displayed based on filtered provider name")
	public void searchTravelByDefaultOptions_selectInsurerName_shouldShowCorrectFilteredResults() {
		searchTravelByDefault();
		String filteredInsurer = travelPage.getTravelFilters().getRandomInsuranceName();
		travelPage.getTravelFilters().filterByInsuranceName(filteredInsurer);
		travelPage.getTravelCardDetails().waitForResultsLoad();

		// Verify displayed results includes selected insurer from the filter list
		assertThat(travelPage.getTravelCardDetails().getAllInsurancesName()).containsExactly(filteredInsurer);
	}

	@Test(description = "Filter - Verify results are displayed based on medical expenses slider")
	public void searchTravelByDefaultOptions_filterByPersonalAccident_shouldShowCorrectFilteredResults() {
		searchTravelByDefault();
		travelPage.getTravelFilters()
				.seeMoreFilters()
				.changeMedicalExpensesFilter(0, 9000000);
		travelPage.getTravelCardDetails().waitForResultsLoad();

		// Verify every displayed insurer cards have medical expenses value in selected range
		travelPage.getTravelCardDetails().getAllMedicalExpenses().forEach(number -> {
			assertThat(number).isBetween(0, 9000000);
		});
	}

	@Test(description = "Sort - Verify results are sorted by price from low to high")
	public void searchTravelByDefaultOptions_sortByPriceLowToHigh_shouldShowCorrectFilteredResults() {
		searchTravelByDefault();
		travelPage.getTravelSort().sortByPriceLowToHigh();
		travelPage.getTravelCardDetails().waitForResultsLoad();

		// Verify every displayed insurer cards have total prices value is sorted
		assertThat(travelPage.getTravelCardDetails().getAllPrices())
				.isSortedAccordingTo(Comparator.comparingInt(o -> o));
	}

	@Test(description = "Details - Verify results are displayed based on start date & end date changes")
	public void searchTravelByDefaultOptions_selectStartDateAndEndDate_shouldShowCorrectFilteredResult() {
		searchTravelByDefault();
		travelPage.getTravelDetailsFilter().changeTravelStartDate("20");
		travelPage.getTravelDetailsFilter().changeTravelEndDate("30");
		String currentStartDateFormatted = DateUtils.convertDateToString("20-05-2020",
				"dd-MM-yyyy", "dd MMM yyyy");
		String currentEndDateFormatted = DateUtils.convertDateToString("30-05-2020",
				"dd-MM-yyyy", "dd MMM yyyy");
		softly.assertThat(travelPage.getTravelDestinationHeader()).contains(currentStartDateFormatted);
		softly.assertThat(travelPage.getTravelDestinationHeader()).contains(currentEndDateFormatted);
		softly.assertAll();
	}

	@Test(description = "Details - Verify results are displayed based on selected destination")
	public void searchTravelByDefaultOptions_selectDestination_shouldShowCorrectFilteredResult() {
		searchTravelByDefault();
		travelPage.getTravelDetailsFilter().changeDestination("Barbados");
		travelPage.getTravelCardDetails().waitForResultsLoad();
		String destination = travelPage.getTravelDetailsFilter().getCurrentSelectedDestination();
		softly.assertThat(destination).isEqualTo("Barbados");
		softly.assertThat(travelPage.getTravelDestinationHeader()).contains("travel to " + destination);
		softly.assertAll();
	}

	private void searchTravelByDefault() {
		browserKeywords.goTo(PropertyUtils.getInstance().getHomePageUrl());
		homePage.searchTravelByDefaultOptions();
		travelPage.getTravelCardDetails().waitForResultsLoad();
	}
}
