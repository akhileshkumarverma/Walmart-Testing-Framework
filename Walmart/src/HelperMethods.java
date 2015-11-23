import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;


public class HelperMethods {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	public HelperMethods(WebDriver d, WebDriverWait w) {
		driver = d;
		if (w == null)
			wait = new WebDriverWait(driver, 30);
		else
			wait = w;
		
	}
	
	public void waitForLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait waitDriver = new WebDriverWait(driver, 30);
		waitDriver.until(pageLoadCondition);
	}
	
	public WebElement waitAndgetElementById(String id) {
		waitForLoad();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		WebElement webElement = getWebElement(By.id(id));
		return webElement;
	}
	
	public WebElement fluentWait(final By locator) {
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(4000, TimeUnit.SECONDS)
	            .pollingEvery(5, TimeUnit.SECONDS)
	            .ignoring(NoSuchElementException.class);

	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	        }
	    });

	    return  foo;
	}
	
	public boolean waitForPageLoad(int waitTimeInSec, final String path) {
	    Wait<WebDriver> wait = new FluentWait<>(driver)
	            .withTimeout(waitTimeInSec, TimeUnit.SECONDS)
	            .ignoring(StaleElementReferenceException.class)
	            .pollingEvery(2, TimeUnit.SECONDS);
	        WebElement isLoaded = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(path)));
	        if (isLoaded != null) {
	            return true;
	        }
	        else{
	        	return false;
	        }
	}
	
	public WebElement getWebElement(By xPath) {
		return driver.findElement(xPath);
	}

	
	public WebElement getElementByClass(String className){
		return driver.findElement(By.className(className));
	}
	
	public WebElement getElementByXPath(String path){
		return driver.findElement(By.xpath(path));
	}
	
	public WebElement getElementById(String id){
		return driver.findElement(By.id(id));
	}
	
	public boolean idElementExists(String id){
		waitForLoad();
		if (!driver.findElements(By.id(id)).isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public WebElement getElementByCSS(String css){
		return driver.findElement(By.cssSelector(css));
	}
}
