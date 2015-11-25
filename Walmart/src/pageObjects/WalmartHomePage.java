package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

//For all methods at home page

public class WalmartHomePage {
	 private static final String SIGN_IN_LINK = "//*[@id='top']/div[3]/div/div/div/div/div[4]/div/div[1]/div[1]/p/span[2]/a";
	 //private static final String SIGN_IN_HEADER = "/html/body/div[2]/section/section[4]/div/div/div/div/div/div/div/h1";

	 public WebDriver driver;
	 public WebDriverWait wait;
	 private WebElement element;
	 
	 
	 public WalmartHomePage(WebDriver driver, WebDriverWait wait){
		 this.driver = driver;
		 this.wait = wait;
	 }
	 
	 //Finds "Sign In" link in navigation bar
	 public WebElement signInNavBar(){
		 element = driver.findElement(By.xpath(SIGN_IN_LINK));
		 return element;
	 }
	 
	 //Clicks "Sign In" link and navigates to "Sign In" page
	 public void clickSignInNav(){
		 ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+ signInNavBar().getLocation().y+")");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGN_IN_LINK)));
		 signInNavBar().click();
		 System.out.println("Navigating to Sign In page...");
		 Reporter.log("Navigated to Sign In page");
	 }

	 
}
