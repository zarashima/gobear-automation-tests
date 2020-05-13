package pages.travel;

import com.google.inject.Inject;
import element.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TravelDetailsFilter extends TravelPage {

	@FindBy(css = "#detailCollapse div > div label[for=gb_radio_9]")
	private WebElement singleTripRadio;

	@FindBy(css = "#detailCollapse div > div label[for=gb_radio_10]")
	private WebElement annualTripRadio;

	@FindBy(css = "#detailCollapse div > div label[for=gb_radio_11]")
	private WebElement justMeRadio;

	@FindBy(css = "#detailCollapse div > div label[for=gb_radio_12]")
	private WebElement myFamilyRadio;

	@FindBy(css = "#detailCollapse div > div label[for=gb_radio_13]")
	private WebElement twoPersonsRadio;

	@FindBy(css = "#detailCollapse div > div label[for=gb_radio_14]")
	private WebElement threePersonsRadio;

	@FindBy(css = "#detailCollapse div > div label[for=gb_radio_15]")
	private WebElement fourPersonsRadio;

	@FindBy(css = "#detailCollapse div > div label[for=gb_radio_16]")
	private WebElement fivePersonsRadio;

	@FindBy(css = "#detailCollapse div > div > button")
	private WebElement destinationDropdown;

	@FindBy(css = "#detailCollapse div[data-gb-name=destinations]")
	private WebElement destinationDiv;

	@FindBy(css = "#detailCollapse input[name=dates-startdate]")
	private WebElement startDateCalendar;

	@FindBy(css = "#detailCollapse input[name=dates-enddate]")
	private WebElement endDateCalendar;

	@FindBy(css = ".datepicker-days > table > tbody")
	private WebElement dateWidget;

	@Inject
	TravelCardDetails travelCardDetails;

	Calendar calendar;

	@Inject
	public TravelDetailsFilter(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void selectSingleTrip() {
		elementKeywords.click(singleTripRadio);
		travelCardDetails.waitForResultsLoad();
	}

	public void selectAnnualTrip() {
		elementKeywords.click(annualTripRadio);
		travelCardDetails.waitForResultsLoad();
	}

	public void selectJustMe() {
		elementKeywords.click(justMeRadio);
		travelCardDetails.waitForResultsLoad();
	}

	public void selectMyFamily() {
		elementKeywords.click(myFamilyRadio);
		travelCardDetails.waitForResultsLoad();
	}

	public void selectTwoPersons() {
		elementKeywords.click(twoPersonsRadio);
		travelCardDetails.waitForResultsLoad();
	}

	public void selectThreePersons() {
		elementKeywords.click(threePersonsRadio);
		travelCardDetails.waitForResultsLoad();
	}

	public void selectFourPersons() {
		elementKeywords.click(fourPersonsRadio);
		travelCardDetails.waitForResultsLoad();
	}

	public void selectFivePersons() {
		elementKeywords.click(fivePersonsRadio);
		travelCardDetails.waitForResultsLoad();
	}

	public void changeDestination(String destination) {
		elementKeywords.click(destinationDropdown);
		WebElement option = destinationDropdown.findElement(By.xpath("//span[contains(text(),'" + destination + "')]/../link"));
		elementKeywords.click(option);
		travelCardDetails.waitForResultsLoad();
	}

	public void changeTravelStartDate(String startDate) {
		calendar = new Calendar(driver, dateWidget);
		calendar.selectStartDate(startDateCalendar, startDate);
		travelCardDetails.waitForResultsLoad();
	}

	public void changeTravelEndDate(String endDate) {
		calendar = new Calendar(driver, dateWidget);
		calendar.selectEndDate(endDateCalendar, endDate);
		travelCardDetails.waitForResultsLoad();
	}

	public String getCurrentSelectedDestination() {
		return elementKeywords.getText(destinationDropdown);
	}

	public String getCurrentStartDate() {
		return elementKeywords.getText(startDateCalendar);
	}

	public String getCurrentEndDate() {
		return elementKeywords.getText(endDateCalendar);
	}
}
