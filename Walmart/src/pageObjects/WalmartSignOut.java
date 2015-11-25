package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WalmartSignOut {
	private static final String ACCOUNT_NAV = "//*[@id='top']/div[3]/div/div/div/div/div[4]/div/div[1]/div[1]/div/a";
	private static final String FLYOUT_MENU = "flyout15";
	private static final String SIGN_OUT_BUTTON = "//*[@id='flyout15']/ul/span[1]/li[6]/a";
	private static final String AFTER_SIGN_OUT = "/html/body/div[2]/section/section[4]/div/div/div/h3";

	public WebDriver driver;
	private WebElement element;
	private WebDriverWait wait;


	public WalmartSignOut(WebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;
	}
	
	public WebElement getSignOutBtn(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(FLYOUT_MENU)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACCOUNT_NAV)));
		element = driver.findElement(By.xpath(SIGN_OUT_BUTTON));
		return element;
	}
	
	public void clickSignOutBtn(){
		getSignOutBtn().click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AFTER_SIGN_OUT)));
	}
}
