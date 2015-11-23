#Walmart.com Automated Testing Framework
###Developed by Dillon Gee

##Technologies
  + Java
  + Selenium 
  + JUnit
  + Eclipse IDE
  + Chrome WebDriver

##Problem
  Be able to automate an end-to-end user e-commerce transaction flow using any open source tool for www.walmart.com with an existing customer on Chrome or Safari browser.

##Solution
  My solution was buidling an automated testing framework using Selenium WebDriver-Java and JUnit on a Eclipse IDE. 
  
  The framework automates the following scenarios in the following order:

      + Login using existing account (User Name: djwars94010@yahoo.com, Password: walmart123)
      + Perform a search on home page from a pool of key words (tv, socks, dvd, toys, iPhone)
      + Identifies an item from the results set that you can add to cart
      + Adds the item to cart
      + Validates that item added is present in the cart and is the only item in the cart

##Test Cases
*Test cases are automated in this order*
  
| Action | Test Commands | Expected Results |
| Go to Walmart.com | TestMethods init() | Chrome Browser opens to Walmart.com's homepage |
| Go to Sign In page | TestMethods  | Expected Results |

##Functional Requirements

##Non-Functional Requirements

##Assumptions

##Existing/Potential Risks

##Future Changes (Tradeoffs and what's missing)





