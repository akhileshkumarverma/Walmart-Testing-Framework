import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.WalmartCart;
import pageObjects.WalmartHomePage;
import pageObjects.WalmartSearch;
import pageObjects.WalmartSignInPage;

//Runs test cases as TestNG. Gets methods from pagObject classes.

public class WalmartRunTest {

	private static final String EMAIL = "djwars94010@yahoo.com";
	private static final String PASSWORD = "walmart123";
	private final static String DRIVER_PATH = "C:/Users/Dillon/workspace/Walmart/src/chrome_driver/chromedriver.exe";
	private static final String HOMEPAGE_URL = "http://www.walmart.com";
	private static final String[] SEARCH_TERMS = {"toys", "dvd", "iPhone", "socks", "tv"};

	private static WebDriver driver;
	private static WebDriverWait wait;


	// Sets up driver and opens browser
	@BeforeTest
	public  void init() throws Exception{
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		driver.get(HOMEPAGE_URL);
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}

	@DataProvider(name = "testData")
	public Object[][] getData() {
		return new Object[][] { {SEARCH_TERMS} };
	}

	@Test (dataProvider = "testData")
	public void testRunner (String[] searchTerms) throws Exception{

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

		//Inputs search terms and searches each for results
		//Goes through each search term and performs a search.
		WalmartSearch search = new WalmartSearch(driver, wait);
		for(int i = 0; i<searchTerms.length; i++){
			search.enterSearchData(searchTerms[i]);
			search.submitSearch();	
			search. waitForResults(searchTerms[i]);

			//For search terms like "toys", changes to Toys department page 
			//and changes the department parameter type to Toys
			//Checks if department parameter type is "All Departments"
			//if not, changes it back to "All Departments"
			if(search.checkDeptType()){
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
		
	}
	
	@AfterTest
	public void exit() throws Exception{
		System.out.println("Exiting...");
		driver.quit();
	}


}
