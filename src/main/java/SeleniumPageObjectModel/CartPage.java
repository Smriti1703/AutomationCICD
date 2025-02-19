package SeleniumPageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
WebDriver driver;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	

	public CartPage(WebDriver driver)
	{
		//initalization the driver
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match =cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckOutPage goToCheckout()
	{
		checkoutEle.click();
		CheckOutPage checkoutpage =new CheckOutPage(driver);
		return checkoutpage ;
	}


}
