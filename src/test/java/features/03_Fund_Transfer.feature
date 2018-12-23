Feature: Parasoft Banking Fund Transfer 

Scenario Outline: Transfer Money 
	Given User is on Login Page 
	When User enter credentials and login 
	Then User is on Homepage 
	And  User is on Transfer fund page 
	And  User waits for 2 seconds 
	When User transfer "<From Account>" to "<To Account>" amount "<Transfer Amount>" 
	Then User receives transfer fund "<Message>"successfully 
	And User waits for 2 seconds 
	And User close the browser 
	
	Examples: 
		|From Account |To Account | Transfer Amount | Message 				|
		| 12900       | 13011	  | 10		   	  	| has been transferred	|
		