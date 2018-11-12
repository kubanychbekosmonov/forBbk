@smoke
Feature: GitHub Search 

Background: 
Given accept and content type are JSON

@test1
Scenario Outline: Validate the search by author name functionality 
When unauthenticated user searches repositories by a valid <author name>
And the user hits the endpoint
Then user should get 200 as a status code
Then user should only see repositories by this <author name> as a result
Examples:
|author name|
|oracle|
|cucumber|
|google|


Scenario: Validate the search by author name and sort by stars range functionality
When an unauthenticated user wants repositories with stars ranging from 15000 to 100000
And the user hits the endpoint
Then user should get 200 as a status code
And repositories with stars in a range of 15000 to 100000 should be displayed

@test1
Scenario Outline: Validate the search by author name and sort by stars functionality
When the user filters results by most stars
And unauthenticated user searches repositories by a valid <author name>
And the user hits the endpoint
Then user should get 200 as a status code
Then user should only see repositories by this <author name> as a result
And repositories with most stars should come first
Examples:
|author name|
|apache|



Scenario Outline: Validate the search by author name and sort by languages 
When the user filters results by certain <language>
And the user hits the endpoint
Then user should get 200 as a status code
And only repositories with this <language> should appear
Examples:
|language  |
| javascript |
| Java      |
| HTML      |


@test1
Scenario Outline: Validate the search by author name and sort by forks functionality
When the user filters results by least forks
And unauthenticated user searches repositories by a valid <author name>
And the user hits the endpoint
Then user should get 200 as a status code
Then user should only see repositories by this <author name> as a result
And repositories with least forks should come first
Examples:
|author name|
|tesla|



Scenario Outline: Validate pagination number of results
When the user paginates results  count by 50 items per page
And unauthenticated user searches repositories by a valid <author name>
And the user hits the endpoint
Then user should get 200 as a status code
Then user should only see repositories by this <author name> as a result
And one page should contain at most 50 items by default
Examples:
|author name|
|facebook|


Scenario Outline: Validate pagination default number of results
When unauthenticated user searches repositories by a valid <author name>
And the user hits the endpoint
Then user should get 200 as a status code
Then user should only see repositories by this <author name> as a result
And one page should contain at most 30 items 
Examples:
|author name|
|cucumber|


