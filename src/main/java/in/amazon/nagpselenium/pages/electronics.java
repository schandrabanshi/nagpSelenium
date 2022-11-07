package in.amazon.nagpselenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import in.amazon.nagpselenium.common.util.Base;
import in.amazon.nagpselenium.reporterLogging.Logging;

public class electronics {

	
public  WebDriver Driver;
	
	@FindBy(id="nav-hamburger-menu")
	WebElement hamburger;
	@FindBy(xpath="//li/a[@class='hmenu-item']/div[text()='TV, Appliances, Electronics']")
	WebElement electronicsIteams;
	
	@FindBy(xpath="//li/a[@class='hmenu-item'][text()='Televisions']")
	WebElement television;
	
	@FindBy(xpath="//img[@alt='TV store']")
	WebElement tvStore;
	
	@FindBy(xpath="//div[@class='a-column a-span12 apb-browse-left-nav apb-browse-col-pad-right a-span-last']//div[@class='a-section a-spacing-small']/span[text()='Discount']")
	WebElement discountSection;
	
	@FindBy(xpath="//span[@class='a-size-base a-color-base'][text()='10% Off or more']")
	WebElement discount;
	
	@FindBy(xpath="//div[@class='a-row a-size-base a-color-base']/span")
	WebElement discountApplied;	
	
	@FindBy(xpath="//div[@class='a-section a-spacing-small puis-padding-left-small puis-padding-right-small']")
	WebElement searchResult;	
	
	@FindBy(id="add-to-cart-button")
	WebElement addToCart;
	
	@FindBy(xpath="div[@id='NATC_SMART_WAGON_CONF_MSG_SUCCESS']/span")
	WebElement addedToCartMessage;
	
	public electronics(WebDriver driver) {		
		this.Driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void navigateToTVStore() {
		Logging.log.info("Navigating to TV Store page.");
		hamburger.click();
		Base.ScrollToView(electronicsIteams);
		Base.Wait(10);
		electronicsIteams.click();		
		Base.Wait(2);
		television.click();		
		
	}
	 public void validateTVStorepage() {
		 Assert.assertTrue(tvStore.isDisplayed(),"TV Store page not displayed");
	 }
	 
	 public void clickOnDiscount() {
		 Base.ScrollToView(discountSection);
		 Base.Wait(10);
		 discount.click();
	 }
	  public String validateDiscountApplied() {
		  return discountApplied.getText();
	  }
	  
	  public void addToCart() {
			Logging.log.info("Opening first search iteam.");
			Base.ScrollToView(searchResult);
			searchResult.click();		
			Base.SwitchToNewWindow();
			Base.Wait(30);
			Logging.log.info("Adding search and selected iteam to cart to buy.");
			Base.ScrollToView(addToCart);	
			addToCart.click();
		}
	  public void validateAddedToCardMessage() {
		  Base.Wait(2);
		  Assert.assertEquals(addedToCartMessage.getText().trim(), "Added to Cart");
	  }
	  
}
