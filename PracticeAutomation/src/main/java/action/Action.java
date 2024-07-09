package action;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Action {
public WebDriver driver;
	
	//Constructor to initialize the WebDriver and PageFactory elements.
	public Action(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this); // Initialize WebElements with PageFactory
	}
	//Enters text into a given web element after waiting for its visibility
	public void entertext(WebElement element, String keyvalue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// Wait for 30 seconds
		wait.until(ExpectedConditions.visibilityOf(element));// Wait until element is visible
		element.clear();// Clear any existing text
		element.sendKeys(keyvalue);// Enter the provided text
	}
	// Clicks a given web element after waiting for its visibility.
	public void click(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// Wait for 30 seconds
		wait.until(ExpectedConditions.visibilityOf(element));// Wait until element is visible
		element.click();// Click the element
	}
	//Selects an option by visible text from a list of dropdown elements.
	public void selectbyvalue(List<WebElement> tileList,String value) {
		for (WebElement element : tileList)  {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Wait for 30 seconds
			wait.until(ExpectedConditions.visibilityOf(element));// Wait until element is visible
			Select select = new Select(element);// Create a Select object for the dropdown
			select.selectByVisibleText(value);// Select the option by visible text
		}
	}
	//Scrolls down the web page by a specified number of pixels.
	public void scrolldrown(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver; // Cast driver to JavascriptExecutor
		js.executeScript("window.scrollBy(0,500)",""); // Execute JavaScript to scroll down by 500 pixels
	}
	//Scrolls down to the particular WebElement 
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	// Waits for an element to become visible.
	public void waitTimeforElementVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Wait for 30 seconds
		wait.until(ExpectedConditions.visibilityOf(element));// Wait until element is visible
	}
	//Waits for an element to become clickable.
	public void waitTimeforElementClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));// Wait for 30 seconds
		wait.until(ExpectedConditions.elementToBeClickable(element));// Wait until element is clickable
	}
	// Waits until either one of the two elements becomes visible.
	public void waitTimeForEitherElement(WebElement element1, WebElement element2)  {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));// Wait for 30 seconds
		ExpectedCondition<WebElement> condition = element -> {
			if (element1.isDisplayed()) {
				return element1; // Return element1 if it is displayed
			} else if (element2.isDisplayed()) {
				return element2; // Return element2 if it is displayed
			}
		return null; // Return null if neither element is displayed
		};
		  wait.until(condition); // Wait until either element1 or element2 is displayed
	}
	//Waits until either one of the two elements and the text on it visbiles
	public void waitTimeForEitherElementText(WebElement element1, WebElement element2) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Wait for 30 seconds
	    ExpectedCondition<Boolean> condition = driver ->  {
	    	boolean element1Condition = element1.isDisplayed() && !element1.getText().isEmpty(); 
	    	boolean element2Condition = element2.isDisplayed() && !element2.getText().isEmpty();
	        
	    	return element1Condition || element2Condition; // Return element if it is displayed
	    };
	    wait.until(condition); // Wait until either element1 or element2 is displayed with text
	}
	// Move the cursor to the element
	public void movetoElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element);
	}
	//Switch to the Frame
	public void switchToFrame(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));// Wait for 30 seconds
		wait.until(ExpectedConditions.visibilityOf(element));
		driver.switchTo().frame(element);
	}	
	// Check the checkbox
	public void checkBox(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));// Wait for 30 seconds
		wait.until(ExpectedConditions.visibilityOf(element));
		if(element.isDisplayed() && element.isEnabled()) {
			element.click();
		}
	}
	public void waitTimeforListElementVisible(List<WebElement> element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Wait for 30 seconds
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
}
