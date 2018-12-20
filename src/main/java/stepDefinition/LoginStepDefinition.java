package stepDefinition;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
	Properties property = new Properties();
	FileInputStream read;
	static Logger log;
	
	@Given("^User is already on Login page$")
	public void User_is_already_on_Login_page() throws IOException {
//		PropertyConfigurator.configure("C:\\Users\\Simran Kaur\\eclipse-workspace\\WebSur\\src\\log4.properties");
		log = Logger.getLogger("rootLogger");

		BasicConfigurator.configure();
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/apps/chromedriver.exe");
		driver = new ChromeDriver();
		log.debug("chrome browser opened");
    	read = new FileInputStream (System.getProperty("user.dir")+"\\src\\config.properties");  
		property.load(read);
		driver.get(property.getProperty("Url"));
		log.debug("navigated to url");
	}
	
	@When("^User enter Username and Password$")
	public void user_enter_creadentials() {
		driver.findElement(By.name("username")).sendKeys(property.getProperty("Username"));
		driver.findElement(By.name("password")).sendKeys(property.getProperty("Password"));

		
	}
	
	@And("^User login successfully$")
	public void User_clicks_on_login() {
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		String lngMsg = driver.findElement(By.xpath("//p[@class='smallText']/b")).getText();
		Assert.assertTrue(lngMsg.contains("Welcome"));
	}

	@Then("^User is on Home Page$")
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

	@Then("^User creates a new account sucessfully with \"(.*)\"$")
	public void user_created_account(String actCreationMsg) {
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		String newActMsg = driver.findElement(By.xpath("//div [@class='ng-scope']/p[1]")).getText();		
		Assert.assertTrue(newActMsg.contains(newActMsg));

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

	@Then("^User receives a \"([^\"]*)\"successfully$")
	public void fund_transfered_successfully(String Tfmsg) {
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.className("title"));
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		String tfcMsg = driver.findElement(By.xpath("//div [@class='ng-scope']/p[1]")).getText();
//		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		Assert.assertTrue(tfcMsg.contains(Tfmsg));
		
	}

	@And("^User close the browser$")
	public void tear_down() {
		driver.quit();

	}

}
