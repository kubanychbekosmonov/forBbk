package implementedSteps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	protected static String author_name;
	protected static Response response;
	protected static RequestSpecification request;
	protected static String URL = "https://api.github.com/search/repositories";
	
	
	
	@Given("^accept and content type are JSON$")
	public void accept_and_content_type_are_JSON() {
		request = RestAssured.given().accept(ContentType.JSON).and().contentType(ContentType.JSON);
	}

	
	
	@When("^an unauthenticated user wants repositories with stars ranging from (\\d+) to (\\d+)$")
	public void an_unauthenticated_user_wants_repositories_with_stars_ranging_from_to(int start, int end) {
		request = request.and().queryParam("q","stars:"+start+".."+end);
	}
	

	
	@When("^the user hits the endpoint$")
	public Response the_user_hits_the_endpoint() {
		response = request.and().params("order","desc","sort","updated").get(URL);
		return response;
	}
	
	

	@Then("^user should get (\\d+) as a status code$")
	public void user_should_get_as_a_status_code(int status_code) {
		Assert.assertEquals(status_code, response.getStatusCode());
	}
	
	

	@Then("^repositories with stars in a range of (\\d+) to (\\d+) should be displayed$")
	public void repositories_with_stars_in_a_range_of_to_should_be_displayed(int start, int end) {
		List <String> listOfStars = new ArrayList();
		
		for(Map s :getResponseBody() ) {      
			listOfStars.add(s.get("stargazers_count").toString());    
			}
		
		for(int i = 0; i <listOfStars.size(); i++){
			if(Integer.parseInt(listOfStars.get(i)) >= start & Integer.parseInt(listOfStars.get(i)) <= end) {
				assertTrue(true);	
			}else{
				assertTrue(false,"Repositories are not in a range of stars given");
			}
			}  
	}
	
	
	
	
	@When("^unauthenticated user searches repositories by a valid (.*)$")
	public void unauthenticated_user_searches_repositories_by_a_valid(String author_name) {
		request = request.queryParam("q", "user:"+author_name);
	}

	
	
	@Then("^user should only see repositories by this (.*) as a result$")
	public void user_should_only_see_repositories_by_this_as_a_result(String author_name) {
	    for(Map s :getResponseBody() ) {
			Assert.assertTrue(s.get("full_name").toString().contains(author_name));
		}
	}

	

	@When("^the user filters results by most stars$")
	public void the_user_filters_results_by_most_stars() {
		request = request.and().params("sort","stars","order", "desc");
	}

	
	
	@Then("^repositories with most stars should come first$")
	public void repositories_with_most_stars_should_come_first() {
		
		List <String> listOfStars = new ArrayList();
		
		for(Map s :getResponseBody() ) {      
			listOfStars.add(s.get("stargazers_count").toString());    
			}
		
		for(int i = 0; i <listOfStars.size()-1; i++){
			if(Integer.parseInt(listOfStars.get(i)) >= Integer.parseInt(listOfStars.get(i+1))) {
				assertTrue(true);	
			}else{
				assertTrue(false,"The results are not sorted by most stars properly");
			}}
	}

	
	
	@When("^the user filters results by least forks$")
	public void the_user_filters_results_by_least_forks() {
		request = request.and().params("sort","forks","order", "asc");
	}

	
	
	@Then("^repositories with least forks should come first$")
	public void repositories_with_least_forks_should_come_first() {
		List <String> listOfForks = new ArrayList();
		
		for(Map s :getResponseBody() ) {      
			listOfForks.add(s.get("forks_count").toString());    
			}
		
		for(int i = 0; i <listOfForks.size()-1; i++){
			System.out.println(Integer.parseInt(listOfForks.get(i))+" <= "+Integer.parseInt(listOfForks.get(i+1)));
			if(Integer.parseInt(listOfForks.get(i)) <= Integer.parseInt(listOfForks.get(i+1))) {
				
				assertTrue(true);
			}else{
				assertTrue(false,"The results are not sorted by fewest forks properly");
			}
			}
	}
	
	
	
	@When("^the user paginates results  count by (\\d+) items per page$")
	public void the_user_paginates_results_count_by_items_per_page(int number_of_results) {
		request = request.and().params("per_page",number_of_results);
	}

	
	
	@Then("^one page should contain at most (\\d+) items by default$")
	public void one_page_should_contain_at_most_items_by_default(int number_of_results) {
		assertTrue(response.jsonPath().getList("items",Map.class).size() <= number_of_results, "The number of results didn't match set number");
	}


	@When("^the user filters results by certain (.*)$")
	public void the_user_filters_results_by_certain(String language) {
		request = request.and().params("q","language:"+language);
	}

	@Then("^only repositories with this (.*) should appear$")
	public void only_repositories_with_this_should_appear(String language) {
		
		for(Map s :getResponseBody() ) {   
			if(s.get("language").toString().equalsIgnoreCase(language)) {
				assertTrue(true);
			}else {
				assertTrue(false,"The repositories are not sorted by language filter properly");
			}
			}
		
	}
	
//	@When("^the user filters results by recently$")
//	public void the_user_filters_results_by_recently() {
//		request = request.and().parameters("order","desc","sort","updated");
//	}
//
//	@Then("^repositories with most recently updated should come first$")
//	public void repositories_with_most_recently_updated_should_come_first() {
//	List <Date> listOfDates = new ArrayList();
//		
//		for(Map s :getResponseBody() ) { 
//			System.out.println(s.get("updated_at"));
//			listOfDates.add(convertStringToDate(s.get("updated_at").toString()));
//			}
//		
//		for(int i = 0; i <listOfDates.size()-1; i++){
//			System.out.println(listOfDates.get(i)+" is before "+listOfDates.get(i+1));
//			if(listOfDates.get(i).before(listOfDates.get(i+1))) {
//				assertTrue(true);
//			}else{
//				assertTrue(false,"The results are not sorted by recently updated properly");
//			}
//			}
//	}
//
//	public Date convertStringToDate(String dateString)
//	{
//	    Date date = null;
//	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//	    try{
//	        date = df.parse(dateString);
//	    }
//	    catch ( Exception ex ){
//	        System.out.println(ex);
//	    }
//	    return date;
//	}

	public List<Map> getResponseBody() {
		List<Map> listOfRepositories = the_user_hits_the_endpoint().jsonPath().getList("items",Map.class);
		return listOfRepositories;
	}

	public void m () {
		
	}
	
	
	

}
