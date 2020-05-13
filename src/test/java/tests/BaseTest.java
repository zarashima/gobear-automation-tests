package tests;

import com.aventstack.extentreports.service.ExtentTestManager;
import com.google.inject.Inject;
import ensure.Wait;
import keywords.Browser;
import keywords.Element;
import modules.DriverModule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.travel.TravelPage;
import utils.ReportUtils;
import webdriver.DriverFactory;

import java.io.File;

@Guice(modules = {
		DriverModule.class
})

public class BaseTest {

	@Inject
	WebDriver driver;

	@Inject
	Browser browserKeywords;

	@Inject
	Element elementKeywords;

	@Inject
	HomePage homePage;

	@Inject
	TravelPage travelPage;

	@Inject
	Wait wait;

	@BeforeSuite
	public void beforeSuite() {
		System.setProperty("extent.reporter.html.out", ReportUtils.getReportFileLocation());
		System.setProperty("extent.reporter.html.start", "true");
		System.setProperty("extent.reporter.html.config", "src"
				+ File.separator + "test"
				+ File.separator + "resources"
				+ File.separator + "html-config.xml");
	}

	@BeforeTest
	public void beforeTest() {
		driver = DriverFactory.getInstance().getDriver();
	}

	@AfterMethod
	public void afterMethod() {
		String browserDetails = ((RemoteWebDriver) driver).getCapabilities().getBrowserName() + "_" +
				((RemoteWebDriver) driver).getCapabilities().getVersion();
		ExtentTestManager.getTest().assignCategory(browserDetails);
		ExtentTestManager.getTest().assignCategory(browserDetails);
	}

	@AfterTest
	public void afterTest() {
		DriverFactory.getInstance().removeDriver();
	}
}
