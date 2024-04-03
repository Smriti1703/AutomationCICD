package StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumPageObjectModel.CartPage;
import SeleniumPageObjectModel.CheckConfirmationPage;
import SeleniumPageObjectModel.CheckOutPage;
import SeleniumPageObjectModel.LandingPage;
import SeleniumPageObjectModel.ProductCatalouge;
import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalouge productCatalouge;
	public CheckConfirmationPage Confimationpage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		//code
		landingPage =launchApplicaton();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password (String username, String password)
	{
		productCatalouge=landingPage.loginApplication(username,password);

	}
	
	@When("^I add the product (.+) to cart$")
	public void I_add_the_product_to_cart(String productName)
	{
		List<WebElement>products =productCatalouge.getproductList();
		productCatalouge.addProductToCart(productName);
	}
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
		CartPage cartpage =productCatalouge.goToCartPage();
		Boolean match =cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		Confimationpage=checkoutpage.submitOrder();
	}
	
	@Then("{String} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string)
	{
		String confirmMessage=Confimationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
}
