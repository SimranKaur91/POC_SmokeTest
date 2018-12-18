package stepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefinition {

	WebDriver driver;
	

	@Given("^User is already on Login page$")
	public void User_is_already_on_Login_page() {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/apps/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://parabank.parasoft.com/parabank/admin.htm");

	}
	
	@When("^User enter \"(.*)\" and \"(.*)\"$")
	public void user_enter_creadentials(String username, String password) {
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
	}
	
	@And("^User clicks on login$")
	public void User_clicks_on_login() {
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		String lngMsg = driver.findElement(By.xpath("//p[@class='smallText']/b")).getText();
		Assert.assertTrue(lngMsg.contains("Welcome"));
	}

	@And("^User is on Home Page$")
	public void User_is_on_Home_Page() {
		String lngMsg = driver.findElement(By.xpath("//p[@class='smallText']/b")).getText();
		Assert.assertTrue(lngMsg.contains("Welcome"));

	}

	@And ("^User navigate to open an account$")
	public void User_open_new_account() {
		driver.findElement(By.linkText("Open New Account")).click();
		String nwAcMsg = driver.findElement(By.className("title")).getText();
		Assert.assertTrue(nwAcMsg.contains("Open New Account"));

	}

	@And("^User selects saving account$")
	public void User_open_saving_account() {
		WebElement acType = driver.findElement(By.id("type"));
		Select selType = new Select(acType);
		selType.selectByVisibleText("SAVINGS");
	}

	@And("^User Selects \"(.*)\"$")
	public void user_choose_account_id(String actid ) {
		WebElement deAccount = driver.findElement(By.id("fromAccountId"));
		Select acID = new Select(deAccount);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		acID.selectByVisibleText(actid);
	}

	@And("^User creates a new account$")
	public void user_created_account() {
		driver.findElement(By.cssSelector("input[type='submit']")).click();

	}

	@And("^User is on Transfer fund page$")
	public void Transfer_find_page() {
		driver.findElement(By.linkText("Transfer Funds")).click();
		String tfMsg = driver.findElement(By.className("title")).getText();
		Assert.assertTrue(tfMsg.contains("Transfer Funds"));

	}

	@When("^User transfer \"([^\"]*)\" to \"([^\"]*)\" amount \"([^\"]*)\"$")
	public void Transfer_funds(String fid, String tid, String amount ) {
		WebElement frmAccount = driver.findElement(By.id("fromAccountId"));
//		System.out.println("my fid value = " + fid + "\nmy tid value = " + tid);
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		Select facID = new Select(frmAccount);
		facID.selectByValue(fid); 
		WebElement toAccount = driver.findElement(By.id("toAccountId"));
		Select tacID = new Select(toAccount);
		tacID.selectByValue(tid);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("amount")).sendKeys(amount);

	}

	@And("^User Transfers the fund successfully$")
	public void fund_transfered_successfully() {
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.className("title"));
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		String tfcMsg = driver.findElement(By.className("title")).getText();
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		Assert.assertTrue(tfcMsg.contains("Transfer Complete!"));
		
	}

	@Then("^User close the browser$")
	public void tear_down() {
		driver.quit();

	}

}
