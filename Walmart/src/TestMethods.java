import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestMethods {
	
	private static final String DRIVER_PATH = "C:/Users/Dillon/workspace/Walmart/src/chrome_driver/chromedriver.exe";
	private static final String HOMEPAGE_URL = "http://www.walmart.com";
	private static final String SIGN_IN_LINK = "//*[@id='top']/div[3]/div/div/div/div/div[4]/div/div[1]/div[1]/p/span[2]/a";
	private static final String EMAIL_FORM_ID = "login-username";
	private static final String PASSWORD_FORM_ID = "login-password";
	private static final String SIGN_IN_SUBMIT = "/html/body/div[2]/section/section[4]/div/div/div/div/div/div/div/form/div/button";
	private static final String ACC_PAGE_HEADER = "/html/body/div[2]/section/section[4]/div/div/div/div/div[2]/div/h1";
	private static final String SEARCH_FORM_ID = "search";
	private static final String SEARCH_RESULTS_ID = "tile-container";
	private static final String SEARCH_BUTTON = "//*[@id='top']/div[3]/div/div/div/div/div[3]/form/div/div[3]/button";
	private static final String DEPT_SELECTOR = "//*[@id='top']/div[3]/div/div/div/div/div[3]/form/div/div[1]/div/button";
	private static final String DEPT_MENU = "//*[@id='top']/div[3]/div/div/div/div/div[3]/form/div/div[1]/div";
	private static final String DEPT_BUTTON= "//*[@id='top']/div[3]/div/div/div/div/div[3]/form/div/div[1]/div/div/ul[1]/li[1]/button";
	private static final String FIRST_ITEM = "//*[@id='tile-container']/div[1]/a";
	private static final String ADD_TO_CART_BUTTON = "WMItemAddToCartBtn";
	private static final String VIEW_CART_ID = "PACViewCartBtn";
	private static final String VIEW_CART = "/html/body/a[1]";
	private static final String ACCOUNT_NAV = "//*[@id='top']/div[3]/div/div/div/div/div[4]/div/div[1]/div[1]/div/a";
	private static final String SIGN_OUT_BUTTON = "//*[@id='flyout19']/ul/span[1]/li[6]/a";
	private static final String AFTER_SIGN_OUT = "/html/body/div[2]/section/section[4]/div/div/div/h3";
	private static final String CHECK_OUT_BUTTON = "CartChkOutBtn";
	private static final String ITEM_LIST = "//*[@id='spa-layout']/div/div/div[1]/div/div[4]/div[2]/div";
	private static final String FIRST_CART_ITEM = "//*[@id='spa-layout']/div/div/div[1]/div/div[4]/div[2]/div[1]";
	private static final String REMOVE_BUTTON_ID = "CartSaveForLaterBtn";
	
	WebDriver driver;
	WebDriverWait wait;
	HelperMethods helper;
	
	public TestMethods() {
		init();
	}
	
	public WebDriver getWebDriver(){
		return driver;
	}

	public void init() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		helper = new HelperMethods(driver, null);
		driver.get(HOMEPAGE_URL);
	}
	
	public void goToSignIn(){
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+helper.getElementByXPath(SIGN_IN_LINK).getLocation().y+")");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGN_IN_LINK)));
		
		helper.getElementByXPath(SIGN_IN_LINK).click();
	}
	
	public void enterLoginInfo(String email, String password){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(EMAIL_FORM_ID)));
		WebElement userField = helper.getElementById(EMAIL_FORM_ID);
		if (userField != null){
			userField.clear();
			userField.sendKeys(email);
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PASSWORD_FORM_ID)));
		WebElement pwField = helper.getElementById(PASSWORD_FORM_ID);
		if (pwField != null){
			pwField.sendKeys(password);
		}
	}
	
	public void submitSignIn(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGN_IN_SUBMIT)));
		helper.getElementByXPath(SIGN_IN_SUBMIT).click();
	}
	
	public void waitForAccPage(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACC_PAGE_HEADER)));
	}
	
	public void waitForResults(){
		driver.manage().timeouts().pageLoadTimeout(60000, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SEARCH_RESULTS_ID)));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FIRST_ITEM)));
	}
	
	public void goBackToAccount(){
		driver.navigate().to("https://www.walmart.com/account/");
	}
	
	public void searchWalmart(String searchTerm){
		helper.waitForLoad();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SEARCH_FORM_ID)));
		WebElement dept = helper.getElementByXPath(DEPT_SELECTOR);
		/*if (!dept.getAttribute("innerHTML").contains(" All ")){
			dept.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DEPT_MENU)));
			helper.getElementByXPath(DEPT_BUTTON).click();
			
		//}
		if (helper.idElementExists(SEARCH_RESULTS_ID)){
			waitForResults();
		}*/
		
		WebElement searchField = helper.getElementById(SEARCH_FORM_ID);
		if (searchField != null){
			helper.waitForLoad();
			searchField.clear();
			searchField.sendKeys(searchTerm);		
		}
	}
	//too fast enters need to wait for page load
	
	public void submitSearch(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_BUTTON)));
		helper.getElementByXPath(SEARCH_BUTTON).click();
		
		WebElement dept = helper.getElementByXPath(DEPT_SELECTOR);
		
		if (dept.getAttribute("innerHTML").contains(" All ") && helper.idElementExists(SEARCH_RESULTS_ID)){
			waitForResults();
		}
	}
	
	public void chooseFirstItem(){
		//((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+helper.getElementByXPath(FIRST_ITEM).getLocation().y+")");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SEARCH_FORM_ID)));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FIRST_ITEM)));
		helper.getElementByXPath(FIRST_ITEM).click();
	}
	
	public void addItemToCart(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ADD_TO_CART_BUTTON)));
		helper.getElementById(ADD_TO_CART_BUTTON).click();
	}
	
	public void viewCart(){
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VIEW_CART)));
		//helper.getElementByXPath(VIEW_CART);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(VIEW_CART_ID)));
		helper.getElementById(VIEW_CART_ID);
	}
	
	public void validateCart(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(CHECK_OUT_BUTTON)));
		List<WebElement> itemList = driver.findElements(By.xpath(ITEM_LIST));
		Assert.assertTrue("More than one item",	itemList != null && itemList.size() == 1);
		
		//Needs to validate that the item added is indeed the same item in the cart
	}
	
	public void removeItemFromCart(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEM_LIST)));
		WebElement remove = helper.getElementById(REMOVE_BUTTON_ID);
		if (remove != null){
			remove.click();
		}
	}
	
	public void signOut(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACCOUNT_NAV)));
		helper.getElementByXPath(SIGN_OUT_BUTTON).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AFTER_SIGN_OUT)));
	}
	
}
