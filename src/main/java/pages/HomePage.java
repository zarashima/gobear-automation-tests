package pages;

import com.google.inject.Inject;
import ensure.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

	@FindBy(xpath = "//a[@href='#Insurance']")
	private WebElement insuranceButton;

	@FindBy(xpath = "//a[@href='#Travel']")
	private WebElement travelButton;

	@FindBy(xpath = "//*[@id='travel-form']//*/button[text()='Show my results']")
	private WebElement showMyResultsButton;

	@FindBy(id = "travel-form")
	private WebElement travelFrom;

	@Inject
	Wait wait;

	@Inject
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public HomePage selectInsuranceTab() {
		elementKeywords.click(insuranceButton);
		return this;
	}

	public HomePage searchTravelByDefaultOptions() {
		selectInsuranceTab();
		elementKeywords.click(travelButton);
		wait.waitForElementDisplay(travelFrom);
		elementKeywords.click(showMyResultsButton);
		return this;
	}

}
