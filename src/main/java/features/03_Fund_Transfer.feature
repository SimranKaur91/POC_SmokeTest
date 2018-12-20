Feature: Parabank Login Fund Transfer 

Scenario Outline: Transfer Money 
	Given User is already on Login page 
	When User enter Username and Password 
	And User login successfully 
	And User is on Home Page 
	And User is on Transfer fund page 
	When User transfer "<From Account>" to "<To Account>" amount "<Transfer Amount>" 
	Then User receives a "<Message>"successfully 
	And User close the browser 
	
	Examples: 
		|From Account |To Account | Transfer Amount | Message 				|
		| 12900       | 13011	  | 10		   	  	| has been transferred	|
