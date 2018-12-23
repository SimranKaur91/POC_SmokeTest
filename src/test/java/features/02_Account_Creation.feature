Feature: Parasoft Banking Account Creation 

Scenario Outline: Open Saving Account 
	Given User is on Login Page 
	When User enter credentials and login 
	Then User is on Homepage 
	And User navigate to open an account 
	And User selects saving account 
	And User waits for 2 seconds 
	And User Selects "<AccountID>" 
	Then User creates a new account successfully with "<Message>" 
	And User close the browser 
	
	Examples: 
		| AccountID  | Message |
		| 13011	   | Congratulations|
		
	