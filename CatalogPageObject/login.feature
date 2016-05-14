Feature: Check login in iBusiness website
	In order to verify that a user is registered in iBusiness
	As a user in iBusiness
	I should be able to login
	
	Scenario: Login
	Given I open iBusiness
	When I enter a valid username and password in the login page
	Then I should get to the main page 
