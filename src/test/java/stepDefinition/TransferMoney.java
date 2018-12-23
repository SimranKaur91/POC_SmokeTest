package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Utilities.Browser;
import Utilities.Log;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TransferMoney {

	WebDriver driver;

	public TransferMoney() {
		driver = Browser.getDriver();
	}

	@And("^User is on Transfer fund page$")
	public void nagivatetoFundTransfer() {
		try {
			driver.findElement(By.linkText("Transfer Funds")).click();
			String tfMsg = driver.findElement(By.className("title")).getText();
			Assert.assertTrue(tfMsg.contains("Transfer Funds"));
			Log.info("User navigated to Fund Transfer Page");
		} catch (Exception e) {
			Log.error("Unable to find element. Error: " + e.getStackTrace());
			driver.quit();
		}

	}

	@When("^User transfer \"([^\"]*)\" to \"([^\"]*)\" amount \"([^\"]*)\"$")
	public void selectFundTransferDetails(String fromAcid, String toAcid, String amount) {
		try {
			WebElement frmAccount = driver.findElement(By.id("fromAccountId"));
			Select facID = new Select(frmAccount);
			facID.selectByValue(fromAcid);
			WebElement toAccount = driver.findElement(By.id("toAccountId"));
			Select tacID = new Select(toAccount);
			tacID.selectByValue(toAcid);
			driver.findElement(By.id("amount")).sendKeys(amount);
			driver.findElement(By.cssSelector("input[type='submit']")).click();
			Log.info("User clicks on fund transfer");
		} catch (Exception e) {
			Log.error("Unable to find element. Error: " + e.getStackTrace());
			driver.quit();
		}

	}

	@Then("^User receives transfer fund \"([^\"]*)\"successfully$")
	public void transferSuccess(String transferMsg) {
		try {
			driver.findElement(By.className("title"));
			String tfcMsg = driver.findElement(By.xpath("//div [@class='ng-scope']/p[1]")).getText();
			Assert.assertTrue(tfcMsg.contains(transferMsg));
			Log.info("Fund transferred successfully");
		} catch (Exception e) {
			Log.error("Unable to find element. Error: " + e.getStackTrace());
			driver.quit();
		}
	}
}
