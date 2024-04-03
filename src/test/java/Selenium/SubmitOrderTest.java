package Selenium;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumPageObjectModel.CartPage;
import SeleniumPageObjectModel.CheckConfirmationPage;
import SeleniumPageObjectModel.CheckOutPage;
import SeleniumPageObjectModel.LandingPage;
import SeleniumPageObjectModel.OrderPage;
import SeleniumPageObjectModel.ProductCatalouge;
import TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
		
		String productName = "ZARA COAT 3";
		WebDriver driver;
		//(dataProvider ="getData",groups = {"Purchase"})
		@Test(dataProvider ="getData")
		public void submitOrder(HashMap<String,String> input) throws IOException
		{
		ProductCatalouge productCatalouge=landingPage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement>products =productCatalouge.getproductList();
		productCatalouge.addProductToCart(input.get("productName"));
		CartPage cartpage =productCatalouge.goToCartPage();
		Boolean match =cartpage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		CheckConfirmationPage Confimationpage=checkoutpage.submitOrder();
		String confirmMessage=Confimationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		}
		 
		//To verify ZARA COAT 3 is displaying in orders page
		//(dependsOnMethods={"submitOrder"})
		
		@Test(dependsOnMethods={"submitOrder"})
		public void orderHistoryTest()
		{
			ProductCatalouge productCatalouge=landingPage.loginApplication("eshu@gmail.com","Abc@123456789");
			OrderPage orderPage=productCatalouge.goToOrdersPage();
//			orderPage.VerifyOrderDisplay(productName);
			Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
		}
		
		
		
		
		//Extent Reports
		
		
		@DataProvider
		public Object[][] getData() throws IOException
		{
			
//			HashMap<String,String> map=new HashMap<String,String>();
//			map.put("email","eshu@gmail.com");
//			map.put("password", "Abc@123456789");
//			map.put("productName", "ZARA COAT 3");
//			
			List<HashMap<String,String>>data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\PurchaseOrder.json");
			return new Object[][] {{data.get(0)}};
			
			
		}
		
		
	}


