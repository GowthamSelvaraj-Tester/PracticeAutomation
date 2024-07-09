package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject {
	
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="username")
	public WebElement username;
	
	@FindBy(id="password")
	public WebElement password;
	
	@FindBy(id="submit")
	public WebElement submitbutton;
	
	@FindBy(id="error")
	public WebElement errormessage;
	
	@FindBy(xpath="//div//a[@href=\"https://practicetestautomation.com/practice-test-login/\"]")
	public WebElement logoutbutton;
}
