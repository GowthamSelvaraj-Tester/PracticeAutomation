package script;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import listener.Listener;
import modules.LoginPageModule;
import utility.MessageReaders;

public class LoginPageScript {
	
	WebDriver driver;
	private LoginPageModule module;
	
	public LoginPageScript(WebDriver driver) {
		this.driver = driver;
		module = new LoginPageModule(driver);
	}
	
	public void TC002_IncorrectUserName() {
		try {
			String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
			
			Map<String,String> testDataMap = module.getdata(testCaseName);
			
			module.enterusername(testDataMap);
			module.enterpassword(testDataMap);
			module.clicksubmit();
			module.checkMessage();
			
			if(module.isErrorMessageDisplayed()) {
				Assert.assertTrue(module.isErrorMessageDisplayed());
				String actualMessage = module.logErrorMessage();
				String expectedMessage = MessageReaders.getProperty("incorrectUserName");
				Assert.assertEquals(actualMessage,expectedMessage);
				Listener.extentTest.get().log(Status.INFO,"Error Message Matched");
			}else {
				Assert.fail("Test Execution failed");
				Listener.extentTest.get().log(Status.FAIL,"Test Case Execution Failed");
			}
		}catch(Exception e) {
			Assert.fail("Test Execution failed");
			Listener.extentTest.get().log(Status.FAIL,"Exception Occured:" + e.getMessage());
		}
	}
	
	public void TC003_IncorrectPassword() {
		try {
			String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
			
			Map<String,String> testDataMap = module.getdata(testCaseName);
			
			module.enterusername(testDataMap);
			module.enterpassword(testDataMap);
			module.clicksubmit();
			if(module.isErrorMessageDisplayed()) {
				Assert.assertTrue(module.isErrorMessageDisplayed());
				String actualMessage = module.logErrorMessage();
				String expectedMessage = MessageReaders.getProperty("incorrectPassword");
				Assert.assertEquals(actualMessage,expectedMessage);
			}else {
				Assert.fail("Test Execution failed");
				Listener.extentTest.get().log(Status.FAIL,"Test Case Execution Failed");
			}
		}catch(Exception e) {
			Assert.fail("Test Execution failed");
			Listener.extentTest.get().log(Status.FAIL,"Exception Occured:" + e.getMessage());
		}
	}
	
	public void TC004_ValidCredentials() {
		try {
			String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
			
			Map<String,String> testDataMap = module.getdata(testCaseName);
			
			module.enterusername(testDataMap);
			module.enterpassword(testDataMap);
			module.clicksubmit();
			if(module.isHomeScreenDisplayed()) {
				Assert.assertTrue(module.isHomeScreenDisplayed());
				Listener.extentTest.get().log(Status.PASS,"User Logged in Sucessfully");
			}else {
				Assert.fail("Test Execution failed");
				Listener.extentTest.get().log(Status.FAIL,"Test Case Execution Failed");
			}
		} catch(Exception e) {
			Assert.fail("Test Execution failed");
			Listener.extentTest.get().log(Status.FAIL,"Exception Occured:" + e.getMessage());
		}
	}

}
