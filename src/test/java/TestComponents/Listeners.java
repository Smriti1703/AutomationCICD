package TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	
	ExtentReports extent =ExtentReporterNG.getReportObect();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal();// Thread Safe
	
	@Override	
	public void onTestStart(ITestResult result)
	
	{
		test =extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//unique thread id error vlaidationTest
	}	
	
	@Override	
	public void onTestSuccess(ITestResult result)
	
	{
		extentTest.get().log(Status.PASS, "Test Passed");
		
	}
	@Override	
	public void onTestFailure(ITestResult result)
	
	{
		
		 extentTest.get().fail(result.getThrowable());
		
		try {
			driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Screenshot, Attach it to the report 
		String filepath = null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());	
		
	}	
		
	@Override	
	public void onTestSkipped(ITestResult result){
			
	}	
	@Override	
	public void onFinish(ITestContext context)
	
	{
		extent.flush();
		
	}	
}
