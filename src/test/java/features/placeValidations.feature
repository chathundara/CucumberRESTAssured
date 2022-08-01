Feature: Validating Place APIs 
##This is 1st testcase
@AddPlace @Regression
Scenario Outline: Verify whether the place being added successfully using AddPlaceAPI 
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "Post" http request 
	Then the API calls gotm success with status code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP"
	And verify place_ID created maps to "<name>" using "getPlaceAPI"
	
	Examples:
	|name|language|address|
	|KRD Chathundara| Sinhala| 145 Athurugiriya|
#	|Ransarini| English| 145 Mattegoda|
	
##This is 2nd testcase
@DeletePlace @Regression
Scenario: Verify if Delete place functionality is working
	Given DeletePlace payload
	When user calls "DeletePlaceAPI" with "Delete" http request
	Then the API calls gotm success with status code 200
	And "status" in response body is "OK"
	
	
	
	
	
	
	
	
	
	
	
	
	
	