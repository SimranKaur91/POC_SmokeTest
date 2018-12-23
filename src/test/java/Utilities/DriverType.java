package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum DriverType {
	
	FIREFOX("Firefox") {
        @Override
        public WebDriver getDriver() {
        	System.setProperty("webdriver.gecko.driver", "geckodriver");
            return new FirefoxDriver();
        }
    },

    CHROME("Chrome") {
        @Override
        public WebDriver getDriver() {
        	System.setProperty("webdriver.chrome.driver", "chromedriver");
            return new ChromeDriver();
            
        }
    };
	
	private final String mType;
	
	DriverType(final String type){
		mType = type;
	}

    public abstract WebDriver getDriver();
    
    public static final DriverType getDriverType(final String type) {
    	
    	final DriverType[] allTypes = values();
    	for(DriverType thisType : allTypes) {
    		if(thisType.mType.equalsIgnoreCase(type)) {
    			return thisType;
    		}
    	}
    	
    	throw new IllegalStateException("Wrong Driver Type: "  + type);
    }
}