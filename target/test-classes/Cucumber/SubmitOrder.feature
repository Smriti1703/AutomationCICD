
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
  
  Background:
  Given I landed on Ecommerce Page
  

  @tag2
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product <productName> to cart
    And checkout <productName> and submit the order 
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | 				name 			 |		password  |			productName   |
      |     eshu@gmail.com | Abc@123456789|		   ZARA COAT 3  |
  
