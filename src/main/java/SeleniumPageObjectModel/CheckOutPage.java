package SeleniumPageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".btnn")
	WebElement submit;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')] [2]")
	WebElement selectCountry;
	
	By result =By.cssSelector(".ta-results");

	public void selectCountry(String string)
	{
		Actions a =new Actions(driver);
		a.sendKeys(country,"india").build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,600)");
				
	}
	
	public CheckConfirmationPage submitOrder()
	{
		submit.click();
		CheckConfirmationPage checkConfimationpage =new CheckConfirmationPage(driver);
		return checkConfimationpage ;
	}
}
