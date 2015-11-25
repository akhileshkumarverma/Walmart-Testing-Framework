import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.WalmartCart;
import pageObjects.WalmartHomePage;
import pageObjects.WalmartSearch;
import pageObjects.WalmartSignInPage;
import pageObjects.WalmartSignOut;


public class WalmartRunTest {
	
	private static final String EMAIL = "djwars94010@yahoo.com";
	private static final String PASSWORD = "walmart123";
	private final static String DRIVER_PATH = "C:/Users/Dillon/workspace/Walmart/src/chrome_driver/chromedriver.exe";
	private static final String HOMEPAGE_URL = "http://www.walmart.com";
	private static final String[] SEARCH_TERMS = {"toys", "dvd", "socks", "tv"};
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	public static void init() throws Exception{
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		driver.get(HOMEPAGE_URL);
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}

	public static void main (String[]args) throws Exception{
		
		init();
		
		//Go to sign in page
		WalmartHomePage home = new WalmartHomePage(driver,wait);
		home.clickSignInNav();
		
		//Sign in to walmart.com
		WalmartSignInPage signIn = new WalmartSignInPage(driver, wait);
		
		if(signIn.getEmailForm() != null){
			signIn.enterEmailInfo(EMAIL);
		}
		
		if(signIn.getPasswordForm() != null){
			signIn.enterPWInfo(PASSWORD);
		}
	
		signIn.clickSignInBtn();
		
		WalmartSearch search = new WalmartSearch(driver, wait);
		
		for(int i = 0; i<SEARCH_TERMS.length; i++){
			search.enterSearchData(SEARCH_TERMS[i]);
			search.submitSearch();
			
			search.waitForResults(SEARCH_TERMS[i]);
			
			if(search.deptType()){
				search.changeDept();
			}
			
		}
		
		//Add first item to cart
		WalmartCart cart = new WalmartCart(driver, wait);
		cart.selectFirstItem();
		String firstItemName = cart.storeItemInfo();
		cart.addToCart();
		
		//Validate that item is item added and only item in cart
		cart.viewCart();
		List<WebElement> cartItems = cart.getItemsInShoppingCart();
		cart.validateItem(firstItemName);
		cart.validateCart(cartItems);
		
		//Close browser
		System.out.println("Exiting...");
		driver.quit();

	}
	

}
