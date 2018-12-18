Feature: Parabank Login 

Scenario Outline: Login to Website 
	Given User is already on Login page 
	When User enter "<Username>" and "<Password>" 
	And User clicks on login 
	And User is on Home Page 
	Then User close the browser 
	
	Examples: 
		| Username | Password|
		| john     | demo    |
