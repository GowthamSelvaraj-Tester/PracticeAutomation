package base;

import java.io.IOException;
import java.util.Collections;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import utility.ConfigReaders;
import utility.GlobalVariable;

public class Base {
	public WebDriver driver;
	
	public WebDriver initializedriver(String URL) throws IOException {
		// Set the system property for ChromeDriver location
		System.getProperty(ConfigReaders.getProperty("Chromedriver"),GlobalVariable.basepath+ConfigReaders.getProperty("chromedriverlocation"));
		ChromeOptions option = new ChromeOptions(); // Create ChromeOptions to customize browser settings
		option.addArguments("--disable-cache");// Disable cache
		option.addArguments("--disable-cookies"); // Disable cookies
		option.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation")); // Exclude automation switches to avoid detection as a bot
		option.setExperimentalOption("useAutomationExtension", false); // Disable use of automation extension
		option.setPageLoadStrategy(PageLoadStrategy.NORMAL);// Set page load strategy to normal
//		option.addArguments("--headless");// Headless mode for running tests without GUI (commented out)
		option.addArguments("--disable-infobars"); // Disable infobars
		//option.addArguments("--incognito"); // Launch browser in incognito mode
		driver = new ChromeDriver(option); // Initialize ChromeDriver with options 
		driver.manage().window().maximize();// Maximize browser window
		driver.manage().deleteAllCookies();// Delete all cookies
		driver.get(URL);// Navigate to the specified URL
		return driver; // Return the WebDriver instance
	}

  public void closebrowser(WebDriver driver){
		driver.quit();// Quit the browser session
	}	

  public WebDriver getDriver() {
    	return driver;// Return the current WebDriver instance
	}

}
