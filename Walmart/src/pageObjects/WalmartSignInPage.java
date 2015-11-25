package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

//For all methods in the "Sign In" page

public class WalmartSignInPage {
	private static final String EMAIL_FORM_ID = "login-username";
	private static final String PASSWORD_FORM_ID = "login-password";
	private static final String SIGN_IN_SUBMIT = "/html/body/div[2]/section/section[4]/div/div/div/div/div/div/div/form/div/button";
	private static final String ACC_PAGE_HEADER = "/html/body/div[2]/section/section[4]/div/div/div/div/div[2]/div/h1";

	public WebDriver driver;
	private WebElement element;
	private  WebDriverWait wait;


	public WalmartSignInPage(WebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;
	}
	
	//Finds "Email Address" input form
	public WebElement getEmailForm(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(EMAIL_FORM_ID)));
		 element = driver.findElement(By.id(EMAIL_FORM_ID));
		 return element;
	}
	
	//Finds "Password" input form
	public WebElement getPasswordForm(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PASSWORD_FORM_ID)));
		element = driver.findElement(By.id(PASSWORD_FORM_ID));
		return element;
	}
	
	//Finds "Sign In" button
	public WebElement getSignInButton(){
    	element = driver.findElement(By.xpath(SIGN_IN_SUBMIT));
		 return element;
    }
	
	//Finds header in account page
	public WebElement getAccountPageHeader(){
		element = driver.findElement(By.id(ACC_PAGE_HEADER));
		return element;
	}
	
	//Enters account email into "Email Address" input form
	public void enterEmailInfo(String email){
		getEmailForm().clear();
		getEmailForm().sendKeys(email);
		System.out.println("Inputting email address...");
		Reporter.log("Email Address entered");
	}

	//Enters account password into "Password" input form
	public void enterPWInfo(String password){
		getPasswordForm().clear();
		getPasswordForm().sendKeys(password);
		System.out.println("Inputting password...");
		Reporter.log("Password entered");
	}

	//Submits email and password and navigates to user's account page
    public void clickSignInBtn(){
    	getSignInButton().click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACC_PAGE_HEADER)));
    	System.out.println("Navigating to account page...");
    	Reporter.log("Navigated to Sign In page");
    }

}
