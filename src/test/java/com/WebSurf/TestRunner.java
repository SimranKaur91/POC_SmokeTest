package com.WebSurf;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import com.github.mkolisnyk.cucumber.runner.RetryAcceptance;

@RunWith(ExtendedCucumber.class)

@ExtendedCucumberOptions(
		jsonReport = "target/cucumber.json",
							retryCount = 1,
							detailedReport = true,
							detailedAggregatedReport = true,
//							overviewReport = true,
							//coverageReport = true,
							jsonUsageReport = "target/cucumber.json",
//							usageReport = true,
//							toPDF = true,
//							excludeCoverageTags = {"@flaky" },
//							includeCoverageTags = {"@passed" },
							outputFolder = "target")

@CucumberOptions(
		features = {"classpath:features",}
//		format = {"json:target/cucumber.json","html:target/site/cucumber-pretty"},
		glue = {"stepDefinition"},
		plugin = {"pretty","html:target/cucumber-html-report","json:target/cucumber.json","pretty:target/cucumber-pretty.txt", "junit:target/cucumber-result.xml",
				"rerun:target/rerun.txt" }
//		dryRun = true,
//		monochrome = true
		)

public class TestRunner {

	@RetryAcceptance
	public static boolean retryCheck(Throwable e) {
		System.out.println("Reson of failure: " + e.getMessage());
		return !e.getMessage().contains("Browsing context has been discarded");
	}
}
