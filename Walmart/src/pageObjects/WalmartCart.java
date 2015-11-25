package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


//For methods on the shopping cart page, involving adding items, viewing shopping cart, and validation of items in the shopping cart

public class WalmartCart {
	private static final String FIRST_ITEM = "//*[@id='tile-container']/div[1]/a";
	private static final String ADD_TO_CART_BUTTON = "WMItemAddToCartBtn";
	private static final String VIEW_CART_ID = "PACViewCartBtn";
	//private static final String VIEW_CART = "/html/body/a[1]";
	private static final String SEARCH_RESULTS_ID = "tile-container";
	//private static final String CHECK_OUT_BUTTON = "CartChkOutBtn";
	private static final String ITEM_LIST = "//*[@id='spa-layout']/div/div/div[1]/div/div[4]/div[2]/div";
	private static final String FIRST_CART_ID= "CartItemInfo";
	//private static final String FIRST_ITEM_NAME = "//*[@id='CartItemInfo']/span";
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
	
	//Finds the first item of the search results
	public WebElement getFirstItemFromResults(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SEARCH_RESULTS_ID)));
		element = driver.findElement(By.xpath(FIRST_ITEM));
		return element;
	}
	
	//Finds item's name in title on item's detail page
	public WebElement getFirstItemName(){
		element = driver.findElement(By.xpath(ITEM_DETAIL));
		return element;
	}
	
	//Finds "View Cart" button element
	public WebElement getShoppingCart(){
		element = driver.findElement(By.id(VIEW_CART_ID));
		return element;
	}

	//Finds "Add To Cart" button
	public WebElement getAddToCartBtn(){
		element = driver.findElement(By.id(ADD_TO_CART_BUTTON));
		return element;
	}
	
	//Finds quantity count element of item
	public WebElement getQuantityofItem(){
		element = driver.findElement(By.xpath(QUANTITY_CHOOSER));
		return element;
	}
	
	//Finds item title link 
	public WebElement getItemInCartLink(){
		element = driver.findElement(By.id(FIRST_CART_ID));
		return element;
	}
	
	//Selects the first item from search results
	//Clicks name link on item and navigates to item's detail page
	public void selectFirstItem(){
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FIRST_ITEM)));
		getFirstItemFromResults().click();
	}
	/*
	public WebElement getRemoveBtn(){
		element = driver.findElement(By.id("CartSaveForLaterBtn"));
		return element;
	}*/
	
	//Gets the name of the selected item
	public String storeItemInfo(){
		return getFirstItemName().getAttribute("innerHTML");
	}
	
	//Clicks "Add To Cart" button and adds item to cart
	//Opens up overlay of shopping cart
	public void addToCart(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ADD_TO_CART_BUTTON)));
		getAddToCartBtn().click();
		System.out.println("Added item to shopping cart");
	}
	
	//Click "View Cart" button in overlay
	public void viewCart(){
		getShoppingCart().click();
		System.out.println("Viewing shopping cart...");
	}
	
	//Returns a list of item elements in the shopping cart 
	public List<WebElement> getItemsInShoppingCart(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CART_LIST)));
		System.out.println("Getting shopping cart items....");
 		return driver.findElements(By.xpath(ITEM_LIST));
	}
	
	//Validates if item was added to shopping cart
	//Checks if cart is not empty and that there is only one item
	//Also checks if the quantity of that item is one
	public void validateCart(List<WebElement> shoppingCart){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEM_LIST)));
		Assert.assertTrue(shoppingCart != null && shoppingCart.size() == 1);
		Assert.assertTrue(Integer.parseInt(getQuantityofItem().getAttribute("innerHTML")) == 1);
	}

	//Validates that item is the item added
	//Clicks item's title link and navigates to item's details
	//Checks that names are the same of that stored when item was selected
	//Then navigates back to shopping cart page
	public void validateItem(String storedName){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEM_LIST)));
		getItemInCartLink().click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEM_DETAIL)));
		String itemInfo = storeItemInfo();
		driver.navigate().back();
		
		Assert.assertTrue(itemInfo.equals(storedName));
	}
	

}
