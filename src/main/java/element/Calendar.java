package element;

import com.google.inject.Inject;
import ensure.Wait;
import keywords.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

public class Calendar {

	@Inject
	WebDriver driver;

	@Inject
	Wait wait;

	Element elementKeywords;

	private WebElement element;

	@Inject
	public Calendar(WebDriver driver, WebElement element) {
		this.driver = driver;
		this.element = element;
		elementKeywords = new Element(driver);
	}

	public void selectStartDate(WebElement element, String strDate) {
		elementKeywords.click(element);
		selectDate(strDate, false);
	}

	public void selectEndDate(WebElement element, String strDate) {
		elementKeywords.click(element);
		selectDate(strDate, true);
	}

	private void selectDate(String strDate, boolean bReverse) {
		List<WebElement> rows = this.element.findElements(By.tagName("tr"));
		if (bReverse) {
			Collections.reverse(rows);
		}

		searchLoop:
		for (WebElement row : rows) {
			List<WebElement> dates = row.findElements(By.tagName("td"));
			for (WebElement date : dates) {
				if (date.getText().equals(strDate)) {
					date.click();
					break searchLoop;
				}
			}
		}
	}
}
