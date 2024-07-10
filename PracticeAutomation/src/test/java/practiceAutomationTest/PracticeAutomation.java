package practiceAutomationTest;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Base;
import listener.Listener;
import script.LoginPageScript;
import utility.ConfigReaders;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
@Listeners(Listener.class)
public class PracticeAutomation {
  
	private Base WebDriverUtil;
	protected WebDriver driver;
	
	public WebDriver getDriver() {
		return driver;
	}
	
	@BeforeSuite
	public void launchApplication() throws Exception {
		WebDriverUtil = new Base();
		driver = WebDriverUtil.initializedriver(ConfigReaders.getProperty("URL"));
	}
	
	@Test(priority=0)
	public void TC002_IncorrectUserName() {
		try {
			LoginPageScript script = new LoginPageScript(driver);
			script.TC002_IncorrectUserName();
		}catch(Exception e) {
			Listener.extentTest.get().log(Status.FAIL,"Exception occured when executing the Invalid Email TestCase:" + e.getMessage());
			Assert.fail("Test case failed due to an exception: " + e.getMessage());
		}
	
	}
  @AfterSuite
  public void afterSuite() {
  }

}
