package api;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber-report",
				"json:target/cucumber.json"},
		features= "./src/test/java/features",
		glue="implementedSteps",
		tags = "@smoke",
		dryRun=false 
)
public class RunnerClass extends AbstractTestNGCucumberTests{
	
}
