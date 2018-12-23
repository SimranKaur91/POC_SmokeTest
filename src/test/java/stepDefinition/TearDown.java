package stepDefinition;

import org.openqa.selenium.WebDriver;

import Utilities.Browser;
import cucumber.api.java.en.And;

public class TearDown {

	WebDriver driver;

	public TearDown() {
		driver = Browser.getDriver();
	}

	@And("^User close the browser$")
	public void tearDown() {
		driver.quit();
	}

}
