package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WalmartSearch {	
	private static final String SEARCH_FORM_ID = "search";
	private static final String SEARCH_RESULTS_ID = "tile-container";
	private static final String SEARCH_BUTTON = "//*[@id='top']/div[3]/div/div/div/div/div[3]/form/div/div[3]/button";
	private static final String DEPT_SELECTOR = "//*[@id='top']/div[3]/div/div/div/div/div[3]/form/div/div[1]/div/button";
	private static final String DEPT_MENU = "//*[@id='top']/div[3]/div/div/div/div/div[3]/form/div/div[1]/div";
	private static final String DEPT_BUTTON= "//*[@id='top']/div[3]/div/div/div/div/div[3]/form/div/div[1]/div/div/ul[1]/li[1]/button";
	private static final String CATEGORY_INFO= "//*[@id='js-category-container']/div[1]/div[1]";
	private static final String RELATED_SEARCH_PATH = "//*[@id='related-search-container']/div[2]/a";
	

	public WebDriver driver;
	private WebElement element;
	private WebDriverWait wait;


	public WalmartSearch(WebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;
	}

	public WebElement getSearchBar(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SEARCH_FORM_ID)));
		element = driver.findElement(By.id(SEARCH_FORM_ID));
		return element;
	}

	public WebElement getDeptType(){
		element = driver.findElement(By.xpath(DEPT_SELECTOR));
		return element;
	}
	
	public WebElement getDeptButton(){
		element = driver.findElement(By.xpath(DEPT_BUTTON));
		return element;
	}

	public WebElement getSubmitBtn(){
		element = driver.findElement(By.xpath(SEARCH_BUTTON));
		return element;
	}

	public boolean deptType(){
		if (!getDeptType().getAttribute("innerHTML").contains(" All ")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void changeDept(){
		getDeptType().click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DEPT_MENU)));
		getDeptButton().click();
	}

	public void enterSearchData(String searchTerm){
		if (getSearchBar() != null){
			getSearchBar().clear();
			getSearchBar().sendKeys(searchTerm);
		}
	}

	public void submitSearch(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_BUTTON)));
		getSubmitBtn().click();
	}

	public void waitForResults(String searchTerm){
		waitForLoad();
		if (!categoryElementExists()){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SEARCH_RESULTS_ID)));
		}
		System.out.println("Show " + searchTerm + " search results" );
	}
	
	private boolean categoryElementExists() {
	    try {
	    	driver.findElement(By.xpath(CATEGORY_INFO));
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return true;
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

}
