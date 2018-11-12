This is Cucumber/TestNG based BDD style framework.
In "features" package you can find a feature file with all scenarios for GitHub Search functionality.
Most scenarios are data driven, which means you can pass any test data in Examples table. 
In "implementedSteps" package you will find the implementations for steps that are in the feature file.
In "api" package there is a runner class to trigger the test.

In order to run the test, go to RunnerClass in api package, right click and run as TestNG.
By default it will run the tests that have @smoke tag.
Which means it will run all the tests, as I configured. If you want to run some specific scenarios, just give some unique @tagName above that scenario, and go to RunnerClass.java and change the tag to yours and run as TestNG.

If you want to run several or all scenarios, last 3-4 scenarios might fail and give 403 error.
This is because of Rate Limiting set by GitHub.
For unauthenticated requests, the rate limit allows for up to 60 requests per hour. 
So when several requests are hitting the API in a short period of time, the API will temporarily reject all attempts for that user with 403 Forbidden






