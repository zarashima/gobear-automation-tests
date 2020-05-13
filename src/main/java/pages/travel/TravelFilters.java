package pages.travel;

import com.google.inject.Inject;
import element.RangeSlider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TravelFilters extends TravelPage {

	@FindBy(css = "#collapseFilter div[data-filter-name='Show All']")
	private WebElement showAllRadio;

	@FindBy(css = "#collapseFilter div[data-filter-name='Promos Only']")
	private WebElement promoOnlyRadio;

	@FindBy(css = "input[id^=gb_checkbox] + label")
	private List<WebElement> insurancesName;

	@FindBy(xpath = "//*[text()='cancel']")
	private WebElement cancelButton;

	@FindBy(css = "#collapseSeemoreBtn")
	private WebElement seeMoreLink;

	@FindBy(xpath = "//div[@id='collapseSeemore']/div[1]/div/div/div[5]")
	private WebElement personalAccidentBeginSlider;

	@FindBy(xpath = "//div[@id='collapseSeemore']/div[1]/div/div/div[6]")
	private WebElement personalAccidentEndSlider;

	@FindBy(xpath = "//input[@id='gb-slider-2']/..")
	private WebElement personalAccidentSlider;

	@FindBy(xpath = "//div[@id='collapseSeemore']/div[2]/div/div/div[5]")
	private WebElement medicalExpenseBeginSlider;

	@FindBy(xpath = "//div[@id='collapseSeemore']/div[2]/div/div/div[6]")
	private WebElement medicalExpenseEndSlider;

	@FindBy(xpath = "//input[@id='gb-slider-2']/..")
	private WebElement medicalExpensesSlider;

	@FindBy(css = "#collapseSeemore #gb-slider-2 ~ b[data-min-value]")
	private WebElement minMedicalExpenseValue;

	@FindBy(css = "#collapseSeemore #gb-slider-2 ~ b[data-max-value]")
	private WebElement maxMedicalExpenseValue;

	RangeSlider rangeSlider;

	@Inject
	public TravelFilters(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void showAll() {
		elementKeywords.click(showAllRadio);
	}

	public void showPromosOnly() {
		elementKeywords.click(promoOnlyRadio);

	}

	public void filterByInsuranceName(String insuranceName) {
		wait.waitForElementsDisplay(insurancesName);
		insurancesName.stream()
				.filter(s -> s.getText().equalsIgnoreCase(insuranceName))
				.findAny()
				.ifPresent(s ->
						driver.findElement(
								By.xpath("//*[starts-with(@id,'gb_checkbox')]/following-sibling::label[text()='" + s.getText() + " ']")).click());
	}

	public List<String> getAvailableInsurancesName() {
		wait.waitForElementsDisplay(insurancesName);
		return insurancesName.stream()
				.map(WebElement::getText)
				.collect(Collectors.toList());
	}

	public String getFirstInsuranceName() {
		return getAvailableInsurancesName().get(0);
	}

	public String getRandomInsuranceName() {
		Random r = new Random();
		int randomIndex = r.nextInt(getAvailableInsurancesName().size());
		return getAvailableInsurancesName().get(randomIndex);
	}

	public TravelFilters discardFilterTip() {
		elementKeywords.click(cancelButton);
		return this;
	}

	public TravelFilters seeMoreFilters() {
		elementKeywords.click(seeMoreLink);
		return this;
	}

	public void changeMedicalExpensesFilter(int minValue) {
		wait.waitForElementDisplay(medicalExpensesSlider);
		rangeSlider = new RangeSlider(driver, medicalExpensesSlider);
		rangeSlider.changeMinValue(medicalExpenseBeginSlider, minValue);
	}

	public void changeMedicalExpensesFilter(int minValue, int maxValue) {
		wait.waitForElementDisplay(medicalExpensesSlider);
		rangeSlider = new RangeSlider(driver, medicalExpensesSlider);
		rangeSlider.changeValues(medicalExpenseBeginSlider, medicalExpenseEndSlider, minValue, maxValue);
	}
}
