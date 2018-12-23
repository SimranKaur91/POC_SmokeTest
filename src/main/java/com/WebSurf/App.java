package com.WebSurf;

import java.util.jar.Attributes.Name;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 * Hello world!
 *
 */
public class App {
	static WebDriver driver;
	static String username = "john";
	static String password = "demo";

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
		driver = new FirefoxDriver();
		driver.get("http://parabank.parasoft.com/parabank/index.htm");

		login(username, password);
		openNewAc();
		tearDown();
	}

	private static void login(String user, String pass) {
		driver.findElement(By.name("username")).sendKeys(user);
		driver.findElement(By.name("password")).sendKeys(pass);
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		String loginMsg = driver.findElement(By.xpath("//p[@class='smallText']/b")).getText();
		Assert.assertTrue(loginMsg.contains("Welcome"));
	}

	private static void openNewAc() {
		driver.findElement(By.linkText("Open New Account")).click();
		String nwAcMsg = driver.findElement(By.className("title")).getText();
		Assert.assertTrue(nwAcMsg.contains("Open New Account"));

		WebElement actType = driver.findElement(By.id("type"));
		Select selAct = new Select(actType);
		selAct.selectByVisibleText("SAVINGS");

		WebElement actNum = driver.findElement(By.id("fromAccountId"));
		Select selActNum = new Select(actNum);
		selActNum.selectByVisibleText("13011");

		driver.findElement(By.cssSelector("input[type='submit']")).click();
	}

	private static void tearDown() {
		driver.quit();
	}
}
