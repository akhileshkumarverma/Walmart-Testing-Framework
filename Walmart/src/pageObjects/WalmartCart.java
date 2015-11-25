package pageObjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WalmartCart {
	private static final String FIRST_ITEM = "//*[@id='tile-container']/div[1]/a";
	private static final String ADD_TO_CART_BUTTON = "WMItemAddToCartBtn";
	private static final String VIEW_CART_ID = "PACViewCartBtn";
	private static final String VIEW_CART = "/html/body/a[1]";
	private static final String SEARCH_RESULTS_ID = "tile-container";
	private static final String CHECK_OUT_BUTTON = "CartChkOutBtn";
	private static final String ITEM_LIST = "//*[@id='spa-layout']/div/div/div[1]/div/div[4]/div[2]/div";
	private static final String FIRST_CART_ID= "CartItemInfo";
	private static final String FIRST_ITEM_NAME = "//*[@id='CartItemInfo']/span";
	private static final String QUANTITY_CHOOSER = "//*[@id='spa-layout']/div/div/div[1]/div/div[4]/div[2]/div/div/div[4]/div/div[1]/div/div/div/div[1]/div/div";
	private static final String ITEM_DETAIL = "/html/body/div[2]/section/section[4]/div/div[2]/div[1]/div[4]/div/h1/span";
	private static final String CART_LIST = "//*[@id='spa-layout']/div/div/div[1]/div/div[4]";
	
	public WebDriver driver;
	private WebElement element;
	private WebDriverWait wait;


	public WalmartCart(WebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;
	}
	
	public WebElement getFirstItemFromResults(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SEARCH_RESULTS_ID)));
		element = driver.findElement(By.xpath(FIRST_ITEM));
		return element;
	}
	
	public WebElement getFirstItemName(){
		element = driver.findElement(By.xpath(ITEM_DETAIL));
		return element;
	}
	
	public WebElement getShoppingCart(){
		element = driver.findElement(By.id(VIEW_CART_ID));
		return element;
	}
	
	public String storeItemInfo(){
		return getFirstItemName().getAttribute("innerHTML");
	}
	
	public WebElement getFirstItemNameInCart(){
		element = driver.findElement(By.xpath(FIRST_ITEM_NAME));
		return element;
	}

	public WebElement getAddToCartBtn(){
		element = driver.findElement(By.id(ADD_TO_CART_BUTTON));
		return element;
	}
	
	public WebElement getQuantityofItem(){
		element = driver.findElement(By.xpath(QUANTITY_CHOOSER));
		return element;
	}
	
	public List<WebElement> getItemsInShoppingCart(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CART_LIST)));
		System.out.println("Getting shopping cart items....");
 		return driver.findElements(By.xpath(ITEM_LIST));
	}
	
	
	public void validateCart(List<WebElement> shoppingCart){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEM_LIST)));
		Assert.assertTrue("More than one item",	shoppingCart != null && shoppingCart.size() == 1);
		Assert.assertTrue("More than one item",	Integer.parseInt(getQuantityofItem().getAttribute("innerHTML")) == 1);
	}
	
	public WebElement getItemInCartLink(){
		element = driver.findElement(By.id(FIRST_CART_ID));
		return element;
	}
	
	
	public void validateItem(String storedName){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEM_LIST)));
		getItemInCartLink().click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEM_DETAIL)));
		String itemInfo = storeItemInfo();
		driver.navigate().back();
		
		Assert.assertTrue("Not the same item as item selected",	itemInfo.equals(storedName));
	}
	
	public void selectFirstItem(){
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_RESULTS_ID)));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FIRST_ITEM)));
		getFirstItemFromResults().click();
	}
	
	public void addToCart(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ADD_TO_CART_BUTTON)));
		getAddToCartBtn().click();
		System.out.println("Added item to shopping cart");
	}
	
	public void viewCart(){
		getShoppingCart().click();
		System.out.println("View shopping cart");
	}
	
}
