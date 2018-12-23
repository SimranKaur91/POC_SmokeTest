package com.WebSurf;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(
		monochrome = true,
	    features = "@target/rerun.txt", //Cucumber picks the failed scenarios from this file 
	    format = {"pretty", "html:target/site/cucumber-pretty",
	            "json:target/cucumber.json"}
		)

public class FailedRunner {

}
