package keywords;

import com.google.inject.Inject;
import ensure.Wait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.LogUtils;
import utils.WebElementUtils;
import webdriver.DriverFactory;

public class Element {

	@Inject
	Wait wait;

	@Inject
	WebDriver driver;

	@Inject
	public Element(WebDriver driver) {
		this.driver = driver;
		wait = new Wait(driver);
	}

	public String getText(WebElement element) {
		wait.waitForElementDisplay(element);
		return element.getText();
	}

	public void click(WebElement element) {
		LogUtils.info("Click on element: " + WebElementUtils.getElementXpathInfo(element));
		wait.waitForElementDisplay(element);
		wait.waitForElementClickable(element);
		scrollIntoView(element);
		element.click();
	}

	public void scrollIntoView(WebElement element) {
		LogUtils.info("Scroll into view of element: " + WebElementUtils.getElementXpathInfo(element));
		((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript("arguments[0].scrollIntoView();", element);
		((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript("window.scrollBy(0, -250);");
	}

}
