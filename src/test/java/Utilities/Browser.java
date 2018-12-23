package Utilities;

import org.openqa.selenium.WebDriver;

import Utilities.DriverType;

public class Browser {

	private static WebDriver driver;
	private static Browser browser;

	private Browser(DriverType driverType) {
		driver = driverType.getDriver();
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static final Browser getBrowser(String mBrowser) {
		if (mBrowser.equals("Firefox")) {
			browser = new Browser(DriverType.FIREFOX);

		} else if (mBrowser.equals("Chrome")) {
			browser = new Browser(DriverType.CHROME);
		}
		return browser;
	}
}
