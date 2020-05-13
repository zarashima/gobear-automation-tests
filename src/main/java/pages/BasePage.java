package pages;

import com.google.inject.Inject;
import ensure.Wait;
import keywords.Browser;
import keywords.Element;
import keywords.Verification;
import org.openqa.selenium.WebDriver;

public class BasePage {

	@Inject
	public Browser browserKeywords;

	@Inject
	public Element elementKeywords;

	@Inject
	public Wait wait;

	@Inject
	public Verification verificationKeywords;

	@Inject
	protected WebDriver driver;

	@Inject
	public BasePage(WebDriver driver) {
		this.driver = driver;

	}
}
