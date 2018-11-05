Feature: GitHub Search 

@smoke
Scenario Outline: Validate the search by author name functionality 
Given accept and content type are JSON
When unauthenticated user searches repositories by a valid <"author name">
Then user should get 200 as a status code
Then user should only see repositories by this <"author name"> as a result
Examples:
|author name|
|apache|










Scenario Outline: Validate the search by author name and sort by stars functionalities 
Given accept and content type are JSON
When unauthenticated user searches repositories by a valid <"author name">
And the user filters results by most stars
Then user should only see repositories by this <"author name"> as a result
And repositories with most stars should come first
Examples:
|author name|
|eniiazov|

Scenario Outline: Validate the search by author name and sort by forks functionalities 
Given accept and content type are JSON
When unauthenticated user searches repositories by a valid <"author name">
And the user filters results by most forks
Then user should only see repositories by this <"author name"> as a result
And repositories with most forks should come first
Examples:
|author name|
|eniiazov|

