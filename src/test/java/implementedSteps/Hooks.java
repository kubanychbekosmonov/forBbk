package implementedSteps;

import java.util.concurrent.TimeUnit;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	@Before
	public void setUp(Scenario scenario) {
		System.out.println("Before scenario");
	}

	@After
	public void tearDown(Scenario scenario) {
		Test1.request = null;
		Test1.response = null;
		System.out.println("After scenario");
	}

}
