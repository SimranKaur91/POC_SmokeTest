package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"classpath:features"},
		glue= {"stepDefinition"},
		plugin={"pretty:target/cucumber-pretty","junit:target/cucumber-result.xml","html:target/cucumber-htlm-report", "json:target/cucumber.json"} )

public class TestRunner {

}
