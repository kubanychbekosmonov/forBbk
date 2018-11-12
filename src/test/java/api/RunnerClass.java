package api;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		plugin = {"pretty", 
				"html:target/cucumber-report",
				"json:target/cucumber.json"},
		features= "./src/test/java/features",
		monochrome = true,
		glue="implementedSteps",
		tags = "@smoke", //change the tag name here, eg: @test1
		dryRun=false 
)
public class RunnerClass extends AbstractTestNGCucumberTests{
	
}
