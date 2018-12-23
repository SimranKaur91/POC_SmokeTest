package stepDefinition;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Utilities.Browser;
import Utilities.Log;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class CreateAccount {

	WebDriver driver;

	public CreateAccount() {
		driver = Browser.getDriver();
		DOMConfigurator.configure("log4j.xml");
	}
	@And ("^User navigate to open an account$")
	public void navigateToOpenNewAccount() {
		try {
			driver.findElement(By.linkText("Open New Account")).click();
			String nwAcMsg = driver.findElement(By.className("title")).getText();
			Assert.assertTrue(nwAcMsg.contains("Open New Account"));
			Log.info("User is on Open New Account Page");
		} catch (Exception e) {
			Log.error("Unable to find element. Error: " + e.getStackTrace());
			driver.quit();
		}

	}

	@And("^User selects saving account$")
	public void selectAccountType() {
		try {
		WebElement acType = driver.findElement(By.id("type"));
		Select selType = new Select(acType);
		selType.selectByVisibleText("SAVINGS");
		}catch(Exception e) {
			Log.error("Unable to find element. Error: " + e.getStackTrace());
			driver.quit();
		}
	}

	

	@And("^User Selects \"(.*)\"$")
	public void selectAccountNumber(String actid) {
		try {
		WebElement actNumb = driver.findElement(By.id("fromAccountId"));
		Select acID = new Select(actNumb);
		acID.selectByVisibleText(actid);
		}catch (Exception e) {
			Log.error("Unable to find element. Error: " + e.getStackTrace());
			driver.quit();
		}
	}

	@Then("^User creates a new account successfully with \"(.*)\"$")
	public void createAccount(String actCreationMsg) {
		try {
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		String newActMsg = driver.findElement(By.xpath("//div [@class='ng-scope']/p[1]")).getText();
		Assert.assertTrue(newActMsg.contains(newActMsg));
	}catch (Exception e) {
		Log.error("Unable to find element. Error: " + e.getStackTrace());
		driver.quit();
	}

	}
	
	@And("^User waits for (\\d+) seconds$")
	public void waitFor(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);

	}

}
