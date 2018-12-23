package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Utilities.Browser;
import Utilities.Log;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

public class LoginSteps {

	private Properties properties;
	private final String propertyFilePath = "config.properties";
	private BufferedReader read;

	WebDriver driver;

	public LoginSteps() {
		try {
			getConfigInfo();
			driver = Browser.getBrowser(properties.getProperty("ChromeBrowser")).getDriver();
			DOMConfigurator.configure("log4j.xml");
		} catch (Exception e) {
			Log.error("Unable to open browser. Error: " + e.getStackTrace());
			driver.quit();
		}
	}

	@Given("^User is on Login Page$")
	public void navigateToLoginPage() {
		try {
			getConfigInfo();
			driver.get(properties.getProperty("URL"));
			read.close();
		} catch (IOException e) {
			Log.error("Unable to close config file. Error: " + e.getStackTrace());
			driver.quit();
		}
	}

	@When("^User enter credentials and login$")
	public void enterCredentials() {
		try {
			By username = By.name("username");
			By password = By.name("password");
			By loginbtn = By.cssSelector("input[type='submit']");

			getConfigInfo();
			driver.findElement(username).sendKeys(properties.getProperty("Username"));
			driver.findElement(password).sendKeys(properties.getProperty("Password"));
			read.close();
			driver.findElement(loginbtn).click();
			Log.info("User has Logged in Successfully");

		} catch (Exception e) {
			Log.error("Unable to find element. Error Line: " + e.getStackTrace());
			driver.quit();
		}

	}

	@Then("^User is on Homepage$")
	public void verifyLogin() {
		try {
			String loginMsg = driver.findElement(By.xpath("//p[@class='smallText']/b")).getText();
			Assert.assertTrue(loginMsg.contains("Welcome"));
			Log.info("User is on Home Page");
		} catch (Exception e) {
			Log.error("Unable to find element. Error Line: " + e.getStackTrace());
			driver.quit();
		}

	}

	private void getConfigInfo() {
		try {
			read = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(read);
				Log.info("Config file is accessed successfully");

			} catch (IOException e) {
				Log.error("Unable to open config file. Error: " + e.getStackTrace());
			}
		} catch (FileNotFoundException e) {
			Log.error("Unable to open config file. Error: " + e.getStackTrace());
			driver.quit();
		}
	}

}
