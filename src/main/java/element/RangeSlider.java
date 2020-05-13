package element;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class RangeSlider {

	@Inject
	WebDriver driver;

	private final Actions actions;

	private final WebElement element;

	@Inject
	public RangeSlider(WebDriver driver, WebElement element) {
		this.driver = driver;
		this.element = element;
		actions = new Actions(driver);
	}

	public void changeMinValue(WebElement minHandle, int value) {
		int currentMinValue = getMinValue(minHandle);
		while (currentMinValue < value) {
			actions.moveToElement(minHandle).clickAndHold().moveByOffset(1, 0).release().perform();
			currentMinValue = getMinValue(minHandle);
		}
	}

	public void changeMaxValue(WebElement maxHandle, int value) {
		int currentMaxValue = getMaxValue(maxHandle);
		while (currentMaxValue > value) {
			actions.moveToElement(maxHandle).clickAndHold().moveByOffset(-1, 0).release().perform();
			currentMaxValue = getMaxValue(maxHandle);
		}
	}

	public void changeValues(WebElement minHandle, WebElement maxHandle, int minValue, int maxValue) {
		changeMinValue(minHandle, minValue);
		changeMaxValue(maxHandle, maxValue);
	}

	public int getMinValue(WebElement minHandle) {
		return Integer.parseInt(minHandle.getAttribute("aria-valuenow"));
	}

	public int getMaxValue(WebElement maxHandle) {
		return Integer.parseInt(maxHandle.getAttribute("aria-valuenow"));
	}
}
