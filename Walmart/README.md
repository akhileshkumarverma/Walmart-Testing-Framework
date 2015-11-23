#Walmart.com Automated Testing Framework
####Developed by Dillon Gee

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

      * Login using existing account (User Name: djwars94010@yahoo.com, Password: walmart123)
      * Perform a search on home page from a pool of key words (tv, socks, dvd, toys, iPhone)
      * Identifies an item from the results set that you can add to cart
      * Adds the item to cart
      * Validates that item added is present in the cart and is the only item in the cart

##Components
  + #####TestMethods.java
    - Specific methods for test cases
  + #####HelperMethods.java
    - Methods used by test case methods
    - For re-usability
    - ex.) findElementById("search");
  + #####WalmartRunTest.java
    - Runs test cases

##Test Cases
*Test cases are automated in this order*
  
| Action                                 | Test Commands                      | Expected Results                                                                                             |
| -------------------------------------- | ---------------------------------- | ------------------------------------------------------------------------------------------------------------ |
| Go to Walmart.com                      | TestMethods init()                 | Chrome Browser opens to Walmart's homepage                                                                   |
| Click "Sign In" in navigation bar      | TestMethods goToSignIn()           | Goes to Sign In Page                                                                                         |
| Enter user email into Sign In form     | TestMethods enterLoginInfo()       | Email shows in "Email address" form                                                                          |
| Enter user password into Sign In form  | TestMethods enterLoginInfo()       | Email shows in "Password" form                                                                               |
| Click Sign In button                   | TestMethods submitSignIn()         | Signs in user and directs to account home                                                                    |
| Enter search term from test data       | TestMethods enterSearchData(terms) | Shows search terms in search form                                                                            |
| Click search button                    | TestMethods submitSearch()         | Shows search results for search term entered                                                                 |
| Select first item in results           | TestMethods chooseFirstItem()      |                                                                                                              |
| Add one item to cart                   | TestMethods addItemToCart()        | Add selected item                                                                                            |
| View shopping cart                     | TestMethods viewCart()             | See items added to cart                                                                                      |
| Check if cart only has one item        | TestMethods validateCart()         | Should be one item in cart (Asserts "true")                                                                  |
| Check if item in cart is item selected | TestMethods validateItem()         | Item in cart should be same as item selected (Asserts "true")                                                |
| Sign out of Walmart.com                | TestMethods signOut()              | Sign out of Walmart.com and be alerted "You've successfully signed out of your Walmart.com account." on page |

##Functional Requirements

##Non-Functional Requirements

##Assumptions

##Existing/Potential Risks

##Future Changes (Tradeoffs and what's missing)





