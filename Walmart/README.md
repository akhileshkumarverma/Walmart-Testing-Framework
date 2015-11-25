#Walmart.com Automated Testing Framework
####Developed by Dillon Gee

##Technologies
  + Java
  + Selenium 
  + JUnit
  + Eclipse IDE
  + Chrome WebDriver
  + TestNG (Ecplise Plugin)

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

##Project Instructions

  ###Installation
    + Open Eclipse and import existing project.
    + Copy chrome_driver folder to folder of preference. 
    + Specify the path of the chrome web driver in the variable DRIVER_PATH in the WalmartRunTest.java.

  ###Run Instructions
    + Run WalmartRunTest.java as TestNG (Run As -> TestNG)
  
  ###Components
    + #####Chrome WebDriver
      - Driver path is src/chrome_driver/chromedriver.exe
    + #####WalmartRunTest.java
      - Runs test cases as TestNG
    + pageObjects folder
      * #####WalmartHomePage.java
        - Contains methods for test cases on home page
      * #####WalmartSignInPage.java
	- Contains methods for test cases on sign in page
      * #####WalmartSearch.java
	- Contains methods for searching test data
      * #####WalmartCart.java
	- Contains methods for test cases on shopping cart page and adding item to cart

##Test Cases
*Test cases are automated in this order*
  
| Action                                 | Test Commands                      | Expected Results                                                                                             |
| -------------------------------------- | ---------------------------------- | ------------------------------------------------------------------------------------------------------------ |
| Go to Walmart.com                      | WalmartRunTest init()              | Chrome Browser opens to Walmart's homepage                                                                   |
| Click "Sign In" in navigation bar      | WalmartHomePage clickSignInNav()   | Goes to Sign In Page                                                                                         |
| Enter user email into Sign In form     | WalmartSignInPage enterEmailInfo() | Email shows in "Email address" form                                                                          |
| Enter user password into Sign In form  | WalmartSignInPage enterPWInfo()    | Email shows in "Password" form                                                                               |
| Click Sign In button                   | WalmartSignInPage clickSignInBtn() | Signs in user and directs to account home                                                                    |
| Enter search term from test data       | WalmartSearch enterSearchInfo(term)| Shows search terms in search form                                                                            |
| Click search button                    | WalmartSearch submitSearch()       | Shows search results for search term entered                                                                 |
| Select first item in results           | WalmartCart selectFirstItem        |                                                                                                              |
| Add one item to cart                   | WalmartCart addTocart()            | Add selected item to shopping cart                                                                           |
| View shopping cart                     | WalmartCart viewCart()             | See items added to cart                                                                                      |
| Check if cart only has one item        | WalmartCart validateCart()         | Should be one item in cart (Asserts "true")                                                                  |
| Check if item in cart is item selected | WalmartCart validateItem()         | Item in cart should be same as item selected (Asserts "true")                                                |
| Sign out of Walmart.com                | WalmartSignOut signOut()           | Sign out of Walmart.com and be alerted "You've successfully signed out of your Walmart.com account." on page |

##Functional Requirements
  + Test cases shall be runnable.
  + Test cases shall be written in Java.
  + Test cases shall be robust.

##Non-Functional Requirements
  + Test cases shall be simple to run.
  + Test cases shall be re-usable
  + Test cases shall be made malleable.
  + Documentation shall be clear and easy to read.

##Assumptions
  + Shopping cart is empty
  + User has an existing Walmart account
  + Quantities over one in cart are considered more than one item

##Existing/Potential Risks
  + Load time may disrupt test flow
    - Inputs search terms faster than results can load
  + Bugs with Walmart.com may disrupt test flow

##Future Changes and Tradeoffs
  + Make framework faster and more efficient
    - 
  + Change way framework finds determines department when searching
  + Rely less on checking innerHTML
  + Add function to remove items not added in automation
  + Add sign out function




