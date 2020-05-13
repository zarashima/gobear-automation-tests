package pages.travel;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

import java.util.List;

public class TravelPage extends BasePage {

	@Inject
	private TravelFilters travelFilters;

	@Inject
	private TravelSort travelSort;

	@Inject
	private TravelDetailsFilter travelDetailsFilter;

	@Inject
	private TravelCardDetails travelCardDetails;

	@FindBy(css = "#travel-quote-list div.card-brand")
	private List<WebElement> cardBrandDiv;

	@FindBy(id = "page-content-wrapper")
	private WebElement resultsDiv;

	@FindBy(css = "div[data-gb-name='loading-status']")
	private WebElement loaderDiv;

	@FindBy(css = "#travel-quote-list p.sub-title")
	private WebElement noResultsText;

	@FindBy(xpath = "//*[@id='wrapper']//div[@data-gb-name='travel-nav-data']/p")
	private WebElement travelDestinationHeader;

	@FindBy(css = "#wrapper div[data-gb-name=travel-nav-data] h5")
	private WebElement plansFoundHeader;

	@Inject
	public TravelPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public TravelFilters getTravelFilters() {
		return travelFilters;
	}

	public TravelSort getTravelSort() {
		return travelSort;
	}

	public TravelDetailsFilter getTravelDetailsFilter() {
		return travelDetailsFilter;
	}

	public TravelCardDetails getTravelCardDetails() {
		return travelCardDetails;
	}

	public String getTravelDestinationHeader() {
		return elementKeywords.getText(travelDestinationHeader);
	}

	public String getPlansFoundHeader() {
		return elementKeywords.getText(plansFoundHeader);
	}

	public String getNoResultsText() {
		return elementKeywords.getText(noResultsText);
	}

	public int getNumberOfDisplayedCards() {
		return cardBrandDiv.size();
	}

}
