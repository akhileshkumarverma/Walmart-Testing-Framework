package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//For methods for searching test data and for search result pages

public class WalmartSearch {	
	private static final String SEARCH_FORM_ID = "search";
	private static final String SEARCH_RESULTS_ID = "search-container";
	private static final String SEARCH_BUTTON = "//*[@id='top']/div[3]/div/div/div/div/div[3]/form/div/div[3]/button";
	private static final String DEPT_SELECTOR = "//*[@id='top']/div[3]/div/div/div/div/div[3]/form/div/div[1]/div/button";
	private static final String DEPT_MENU = "//*[@id='top']/div[3]/div/div/div/div/div[3]/form/div/div[1]/div";
	private static final String DEPT_BUTTON= "//*[@id='top']/div[3]/div/div/div/div/div[3]/form/div/div[1]/div/div/ul[1]/li[1]/button";
	private static final String CATEGORY_INFO= "//*[@id='js-category-container']/div[1]/div[1]";
	//private static final String RELATED_SEARCH_PATH = "//*[@id='related-search-container']/div[2]/a";

	public WebDriver driver;
	private WebElement element;
	private WebDriverWait wait;


	public WalmartSearch(WebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;
	}

	//Finds search input form
	public WebElement getSearchBar(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SEARCH_FORM_ID)));
		element = driver.findElement(By.id(SEARCH_FORM_ID));
		return element;
	}

	//Finds department selector for search in search bar
	public WebElement getDeptType(){
		element = driver.findElement(By.xpath(DEPT_SELECTOR));
		return element;
	}
	
	//Finds "All Departments" in department menu 
	public WebElement getAllDeptButton(){
		element = driver.findElement(By.xpath(DEPT_BUTTON));
		return element;
	}

	//Finds search button to submit search query
	public WebElement getSubmitBtn(){
		element = driver.findElement(By.xpath(SEARCH_BUTTON));
		return element;
	}
	
	//Clicks search button ands show search query results
    public void submitSearch(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_BUTTON)));
		getSubmitBtn().click();
	}
	
    //Checks the department type parameter if it is "All Departments"
    //Returns true if not, returns false if it is
	public boolean checkDeptType(){
		if (!getDeptType().getAttribute("innerHTML").contains(" All ")){
			return true;
		}
		else{
			return false;
		}
	}
	
	//Clicks department selector and clicks "All Departments"
	//Changes department type parameter
	public void changeDept(){
		getDeptType().click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DEPT_MENU)));
		getAllDeptButton().click();
	}

	//Enters search terms into search input form
	public void enterSearchData(String searchTerm){
		if (getSearchBar() != null){
			getSearchBar().clear();
			getSearchBar().sendKeys(searchTerm);
		}
	}

	//Waits for search results
	//Checks to see if page is a category page or search results page
	//If not category page, waits for search results to load
	public void waitForResults(String searchTerm){
		waitForLoad();
		if (!categoryElementExists()){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SEARCH_RESULTS_ID)));
		}
		System.out.println("Showing " + searchTerm + " search results" );
	}
	
	//Checks to see if redirect message exists for special cases like "toys"
	private boolean categoryElementExists() {
	    try {
	    	driver.findElement(By.xpath(CATEGORY_INFO));
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return true;
	}
	
	//Waits for page to load
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
