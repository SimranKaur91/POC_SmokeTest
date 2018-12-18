package com.WebSurf;

import static org.testng.Assert.assertTrue;
import java.awt.SecondaryLoop;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 * Hello world!
 *
 */
public class App {

	static WebDriver driver;

	public static void main(String[] args) {

	//	System.setProperty("webdriver.gecko.driver", "C:/Selenium/apps/geckodriver.exe");
    	System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\apps\\chromedriver.exe");

		driver = new FirefoxDriver();
     	WebDriver cdriver = new ChromeDriver();
//    	cdriver.get("http://google.com");
		driver.get("http://parabank.parasoft.com/parabank/admin.htm");
		
		login("john", "demo");
		openAccount();
		transferFund();
		tearDown();
	}

	private static void login(String user, String pass) {
		driver.findElement(By.name("username")).sendKeys(user);
		driver.findElement(By.name("password")).sendKeys(pass);
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		String lngMsg = driver.findElement(By.xpath("//p[@class='smallText']/b")).getText();
		Assert.assertTrue(lngMsg.contains("Welcome"));
	}

	private static void openAccount() {
		driver.findElement(By.linkText("Open New Account")).click();
		
		String nwAcMsg = driver.findElement(By.className("title")).getText();
		Assert.assertTrue(nwAcMsg.contains("Open New Account"));
		
		WebElement acType = driver.findElement(By.id("type"));
		Select selType = new Select(acType);
		selType.selectByVisibleText("SAVINGS");
		
		implicitWait(2);
		WebElement deAccount = driver.findElement(By.id("fromAccountId"));
		Select acID = new Select(deAccount);
		acID.selectByVisibleText("13011");
		
		driver.findElement(By.cssSelector("input[type='submit']")).click();
	}

	private static void transferFund() {
		driver.findElement(By.linkText("Transfer Funds")).click();
		
		String tfMsg = driver.findElement(By.className("title")).getText();
		Assert.assertTrue(tfMsg.contains("Transfer Funds"));
		
		WebElement frmAccount = driver.findElement(By.id("fromAccountId"));
		implicitWait(2);
		Select facID = new Select(frmAccount);
		facID.selectByValue("12900");
		
		WebElement toAccount = driver.findElement(By.id("toAccountId"));
		implicitWait(2);
		Select tacID = new Select(toAccount);
		tacID.selectByValue("13011");
		
		driver.findElement(By.id("amount")).sendKeys("10");
		
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		
		driver.findElement(By.className("title"));
		String tfcMsg = driver.findElement(By.className("title")).getText();
		Assert.assertTrue(tfcMsg.contains("Transfer Complete!"));

	}
	
    private static void implicitWait(int time) {
    	
    	driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }
    
    private static void tearDown() {
    	driver.close();
    }
}
