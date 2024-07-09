package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {
	// Method to take a screenshot and save it to a specified location
	public static String takeScreenshot(WebDriver driver,String testCaseName) {
		TakesScreenshot ts = (TakesScreenshot) driver;// Cast the WebDriver object to TakesScreenshot
		File source = ts.getScreenshotAs(OutputType.FILE); // Capture the screenshot as a file
		String timestamp = DateUtility.getStringDate("_ddMMyyyy_HHmmss");// Generate a timestamp for the screenshot file name
		// Construct the destination file path using the test case name, timestamp, and file format from config
		String destination = ResourceUtility.getScreenshotPath() + testCaseName + "_" + timestamp + ConfigReaders.getProperty("fileFormat");
		try {
			FileUtils.copyFile(source, new File(destination));// Copy the screenshot file to the destination path
		} catch (IOException e) {
			Log.error("Exception occurred in takeScreenShot: " + e.getMessage());// Log an error message if an exception occurs during file copy
		}
		return destination; // Return the destination file path
	}
}
