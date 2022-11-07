# Title
HOME ASSIGNMENT - NAGP 2022 QA Batch Workshop on Selenium Automation

#### Description

This is NAGP Selenium Automation framework for https://www.amazon.in to verify different pages. 
It verify following pages:
1. Amazon home page
	- Amazon Logo,
	- Sign in options,	
	- Items categories,	
	- Cart,
	- Categoty selections.
	
2. Cutomer Servive page
3. Delivery zip location
4. SignIn page
5. Sign-up (Create Account) page
6. Item search and adding yo cart
7. Cart Page
8. Amazon Prime page

#### Install and Run the Project
Install Java (jdk) , TestNG , Maven and add to environment variable and path.
Note: Java version 1.8 used for source and target to run using command line.

##### Run using Eclipse IDE
Four testing .xml files have been added.
1. testingBrowserChrome.xml
	- This xml used to run all test on chrome browser.
2. testingBrowserFireFox.xml
	- This xml used to run test based on FireFox group on firefox browser.
3. testingBrowserEdge.xml
	- This xml used to run test on edge browser.
4. testingParallelThread.xml
	- This xml used to run test parallely on chrome and edge browser.
	
##### Run using command prompt

use *mvn  test* command to run using command prompt.
* Open cmd and navigate to location where pom.xml file exist.	
	ex: cd C:\Users\<user>\eclipse-workspace\in.amazon.nagpselenium	
* Make sure Maven and java installed and added location to enviornment path with correct version.	
* execute *mvn  test* command.
	
#### Use the Framework.
* Common util package
	- Base.java : Containg all the basic functions related to drivers.	
	- FileUtility.java: Containing all common function for reading different files.
* Listeners package
	- Listener.java has been implemented for reporting and capturing screenshot in case of failures.
* Pages
	- Implemented Page object model(POM) with page factory concept for each pages.

* Logging and Reporting
	- ExtentManager.java & ExtentTestManager.java have been created to generate extent report.
	- Logging.java is created for logging into file as well as console.
* Resource->Files

There are 5 files created. 

	- config.properties (Contains generic values : test url, max wait time etc.)
	- log4j2.xml (Contains looging information for console and to TestExecution.log file)
	- signupDataFile.xlsx (Contains user details for creating account for multiple users)
	- testCase.properties (Contains all values i.e used to verify/validate while running test cases)
	- testData.properties (Contains information for validating different page title and message)

* Test-BaseTest class
	- Initializing browser, opening test page, instantiating page fatory and closing all driver as well as page objects.
* Test Cases 

Added four test class

	- Verifying Amazon common functionalities.
	- Validating Amazon cart.
	- Validating SignIn and Create Account.
	- Verifying Amazon Prime.
	

#### Include Tests

Following @Test are added to validate all pages under above testCase classes.
************************************
* TC_ValidateHomePage
* TC_VeriyCustomerService
* TC_SetDeliveryTo_ZipCode_Location

************************************
* TC_SearchAndAddToCart
* TC_VerifyCart

************************************
* TC_Login
* TC_CreateNewAccount

************************************
* TC_VerifyPrimeMembership

#### Report
Generating extent report and attaching failed test case with screenshot under execution datetime folder.
* current_test_results
	- Saving current/latest execution report *.html* file and failed test case screenshot *.png* image.
* archived_test_results
	- Saving all old execution report.

#### Logs
Logging logs (*using log4j*) in logs->TestExecution.log file as well as in console.
