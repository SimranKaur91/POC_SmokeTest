Feature: Parasoft Banking Login

Scenario: Login to Website
Given User is on Login Page
When User enter credentials and login
Then User is on Homepage
And User close the browser