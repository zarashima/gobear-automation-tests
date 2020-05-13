package pages.travel;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TravelSort extends TravelPage {

	@FindBy(id = "gb_radio_2")
	private WebElement promotionRadio;

	@FindBy(css = "#collapseTwo > div > div:nth-child(2) > div > label")
	private WebElement priceLowToHighRadio;

	@FindBy(css = "#collapseTwo > div > div:nth-child(3) > div > label")
	private WebElement priceHighToLowRadio;

	@FindBy(css = "#collapseTwo > div > div:nth-child(4) > div > label")
	private WebElement coverageScoreHighToLowRadio;

	@FindBy(css = "#collapseTwo > div > div:nth-child(5) > div > label")
	private WebElement insuranceNameAscRadio;

	@FindBy(css = "#collapseTwo > div > div:nth-child(6) > div > label")
	private WebElement insuranceNameDescRadio;

	@FindBy(css = "#collapseTwo > div > div:nth-child(7) > div > label")
	private WebElement reviewScoreHighToLowRadio;

	@Inject
	public TravelSort(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public TravelSort sortByPromotion() {
		elementKeywords.click(promotionRadio);
		return this;
	}

	public TravelSort sortByPriceLowToHigh() {
		elementKeywords.click(priceLowToHighRadio);
		return this;
	}

	public TravelSort sortByPriceHighToLow() {
		elementKeywords.click(priceHighToLowRadio);
		return this;
	}

	public TravelSort sortByCoverageScore() {
		elementKeywords.click(coverageScoreHighToLowRadio);
		return this;
	}

	public TravelSort sortByInsuranceNameAsc() {
		elementKeywords.click(insuranceNameAscRadio);
		return this;
	}

	public TravelSort sortByInsuranceNameDesc() {
		elementKeywords.click(insuranceNameDescRadio);
		return this;
	}

	public TravelSort sortByReviewScore() {
		elementKeywords.click(reviewScoreHighToLowRadio);
		return this;
	}
}
