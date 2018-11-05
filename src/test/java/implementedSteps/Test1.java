package implementedSteps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.*;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import cucumber.api.java.en.*;
import utilities.APITrigger;

public class Test1 {
	@Given("^accept and content type are JSON$")
	public RequestSpecification accept_and_content_type_are_JSON() {
		 request = RestAssured.given().accept(ContentType.JSON).and().contentType(ContentType.JSON); 
		 return request;
	}

	@When("^unauthenticated user searches repositories by a valid (.*)$")
	public void unauthenticated_user_searches_repositories_by_a_valid(String author_name) {
		System.out.println(request);
	    response = accept_and_content_type_are_JSON().parameters("q", "user:"+author_name).get("https://api.github.com/search/repositories");
	}

	@Then("^user should get (\\d+) as a status code$")
	public void user_should_get_as_a_status_code(int status_code) {
		Assert.assertEquals(status_code, response.getStatusCode());
	}

	@Then("^user should only see repositories by this (.*) as a result$")
	public void user_should_only_see_repositories_by_this_as_a_result(String author_name) {
	Map responseBody = response.body().as(Map.class);
		
		for (Object key : responseBody.keySet()) {
			if(responseBody.get("items.full_name").toString().contains(author_name)) {
				assertTrue(true);
			}assertTrue(false, "The repository doesn't contain author name");
		}
	}

	
	
	
	
	
	
	
//	@Given("^Unauthenticated user goes to GitHub$")
//	public void unauthenticated_user_goes_to_GitHub() {
//	   request = RestAssured.given().accept("JSON").contentType("JSON");
//	}
//
//	@When("^User searches repositories by existing eniiazov$")
//	public void user_searches_repositories_by_existing_eniiazov() {
//		String authorName="";
//		Map <String, String> parameters = new HashMap();
//		parameters.put("q", "user:"+authorName);
//		parameters.put("sort", "updated");
//		parameters.put("order", "asc");
//		
//		response = request.parameters(parameters).get("url");
//	}
//
//	@Then("^User gets (\\d+) as a status code$")
//	public void user_gets_as_a_status_code(int arg1) {
//	   
//	}
//
//	@Then("^User sees a list of repositories of the author$")
//	public void user_sees_a_list_of_repositories_of_the_author() {
//	 
//	}
//
//	@Then("^User is able to sort the results by$")
//	public void user_is_able_to_sort_the_results_by() {
//	 
//	}
	
	
	
	private static Response response;
	private static RequestSpecification request;

}
