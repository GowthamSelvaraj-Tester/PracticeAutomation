package modules;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import action.Action;
import listener.Listener;
import pageObject.LoginPageObject;
import utility.ConfigReaders;
import utility.ExcelUtility;
import utility.ResourceUtility;

public class LoginPageModule {

	WebDriver driver;
	
	private LoginPageObject element;
	private Action reuse;
	
	public LoginPageModule(WebDriver driver) {
		this.driver = driver;
		this.element = new LoginPageObject(driver);
		this.reuse = new Action(driver);
	}
	
	private String sheetpath = ResourceUtility.getFolderPath();
	private String sheetName = ConfigReaders.getProperty("testDataSheetName");
	
	public Map<String,String> getdata(String testCaseName) throws Exception{
		return new ExcelUtility().getdata(testCaseName,sheetpath, sheetName);
	}
	
	public void enterusername(Map<String,String>testDataMap) {
		try {
			reuse.movetoElement(element.username);
			String username = testDataMap.get(ConfigReaders.getProperty("username"));
			Listener.extentTest.get().log(Status.INFO,"Entering username:" + username);
			reuse.entertext(element.username, username);
		}catch (Exception e) {
			Listener.extentTest.get().log(Status.FAIL,"Exception Occured:" +e.getMessage());
		}
	}
	
	public void enterpassword(Map<String,String>testDataMap) {
		try {
			reuse.movetoElement(element.password);
			String password = testDataMap.get(ConfigReaders.getProperty("password"));
			Listener.extentTest.get().log(Status.INFO,"Entering password:" +password);
			reuse.entertext(element.password, password);
		} catch(Exception e) {
			Listener.extentTest.get().log(Status.FAIL,"Exception Occured:" +e.getMessage());
		}
	}
	
	public void clicksubmit() {
		try {
			reuse.movetoElement(element.submitbutton);
			reuse.click(element.submitbutton);
			Listener.extentTest.get().log(Status.INFO,"Submit button clicked");
		} catch(Exception e) {
			Listener.extentTest.get().log(Status.FAIL,"Exception Occured:" +e.getMessage());
		}
	}
	public void checkMessage() {
		try {
			reuse.scrolldrown(driver);
			Thread.sleep(3600);
		}catch(Exception e) {
			Listener.extentTest.get().log(Status.FAIL,"Exception Occured:" +e.getMessage());
		}
	}
	public boolean isErrorMessageDisplayed() {
		try {
			return element.errormessage.isDisplayed();
		}
		catch (Exception e) {
			Listener.extentTest.get().log(Status.FAIL,"Exception occured:" + e.getMessage());
			return false;
		}
	}
	
	public boolean isHomeScreenDisplayed() {
		try {
			return element.logoutbutton.isDisplayed();
		} catch(Exception e) {
			Listener.extentTest.get().log(Status.FAIL,"Exception occured:" + e.getMessage());
			return false;
		}
	}
	
	public String logErrorMessage() {
		return element.errormessage.getText();
	}
	
}
