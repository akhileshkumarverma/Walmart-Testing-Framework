import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class WalmartRunTest {
	
	private static final String EMAIL = "djwars94010@yahoo.com";
	private static final String PASSWORD = "walmart123";
	private final static String DRIVER_PATH = "C:/Users/Dillon/workspace/Walmart/src/chrome_driver/chromedriver.exe";
	private static final String HOMEPAGE_URL = "http://www.walmart.com";
	private static final String[] SEARCH_TERMS = {"toys", "dvd", "socks", "tv"};
	
	
	private static WebDriver driver;
	private static TestMethods tm;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		tm = new TestMethods();
		driver = tm.getWebDriver();
		driver.get(HOMEPAGE_URL);
		
		tm.goToSignIn();
		tm.enterLoginInfo(EMAIL, PASSWORD);
		tm.submitSignIn();
		tm.waitForAccPage();
		
		for(int i = 0; i<SEARCH_TERMS.length; i++){
			tm.waitForAccPage();
			tm.searchWalmart(SEARCH_TERMS[i]);
			tm.submitSearch();
			driver.manage().timeouts().pageLoadTimeout(60000, TimeUnit.SECONDS);
			if(i != SEARCH_TERMS.length-1){
				tm.goBackToAccount();
			}
		}
		
		tm.chooseFirstItem();
		tm.addItemToCart();
		tm.viewCart();
		tm.validateCart();
		
	}

}
