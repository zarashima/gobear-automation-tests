package pages.travel;

import com.google.inject.Inject;
import ensure.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class TravelCardDetails extends TravelPage {

	@FindBy(css = "#travel-quote-list div[data-gb-name=travel-plan] h4")
	private List<WebElement> insurancesName;

	@FindBy(css = "#travel-quote-list span.value")
	private List<WebElement> totalPriceValue;

	@FindBy(css = "#travel-quote-list")
	private WebElement resultsList;

	@FindBy(xpath = "//p[text()='Medical expenses while traveling']//following-sibling::p/strong/span")
	private List<WebElement> medicalExpensesValue;

	@FindBy(css = "div[data-gb-name=loading-status]")
	private WebElement loadingSection;

	@Inject
	Wait wait;

	@Inject
	public TravelCardDetails(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public List<Integer> getAllPrices() {
		wait.waitForElementsDisplay(totalPriceValue);
		return totalPriceValue.stream()
				.map(WebElement::getText)
				.map(price -> price.replace(",", "").trim())
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	public List<String> getAllInsurancesName() {
		wait.waitForElementsDisplay(insurancesName);
		return insurancesName.stream()
				.map(WebElement::getText)
				.distinct()
				.collect(Collectors.toList());
	}

	public List<Integer> getAllMedicalExpenses() {
		wait.waitForElementsDisplay(medicalExpensesValue);
		return medicalExpensesValue.stream()
				.map(WebElement::getText)
				.map(price -> price.replace(",", "").trim())
				.map(price -> price.replace("₱", "").trim())
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	public void waitForResultsLoad() {
		wait.waitForResultsLoad(resultsList);
		wait.waitUntilDisplayIsNone(loadingSection);
	}
}
