Feature: Parabank Login Fund Transfer 

Scenario Outline: Transfer Money 
	Given User is already on Login page 
	When User enter "<Username>" and "<Password>" 
	And User clicks on login 
	And User is on Home Page 
	And User is on Transfer fund page 
	When User transfer "<From Account>" to "<To Account>" amount "<Transfer Amount>" 
	And User Transfers the fund successfully 
	Then User close the browser 
	
	Examples: 
		| Username | Password | From Account |To Account | Transfer Amount|
		| john     | demo     | 12900      	 | 13011	 | 10			  |
