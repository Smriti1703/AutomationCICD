package Selenium;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import SeleniumPageObjectModel.CartPage;
import SeleniumPageObjectModel.CheckConfirmationPage;
import SeleniumPageObjectModel.CheckOutPage;
import SeleniumPageObjectModel.LandingPage;
import SeleniumPageObjectModel.ProductCatalouge;
import TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {
		
		WebDriver driver;
		//(groups = {"ErrorHandling"})
		@Test
		public void LoginErrorValidation() throws IOException
		{
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("eshu@gmail.com","Abc123456789");
		//div[@class='ng-tns-c4-14 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		}
		
		@Test
		public void ProductOrderValidation() throws IOException
		{
		String productName = "ZARA COAT 3";
		ProductCatalouge productCatalouge=landingPage.loginApplication("tyu@gmail.com","Abc@123456789");
		List<WebElement>products =productCatalouge.getproductList();
		productCatalouge.addProductToCart(productName);
		CartPage cartpage =productCatalouge.goToCartPage();
		Boolean match =cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
		
		}
		
	}


