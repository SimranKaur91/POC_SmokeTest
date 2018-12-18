Feature: Parabank Login Account Creation 

Scenario Outline: Open Saving Account 
	Given User is already on Login page 
	When User enter "<Username>" and "<Password>" 
	And User clicks on login 
	And User is on Home Page 
	And User navigate to open an account 
	And User selects saving account 
	And User Selects "<AccountID>" 
	And User creates a new account 
	Then User close the browser 
	
	Examples: 
		| Username     | Password | AccountID  |
		| john         | demo     | 13011	   |
		
		
		
		
		
