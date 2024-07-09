package listener;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import report.ExtentReportNG;
import utility.GlobalVariable;
import utility.ScreenshotUtility;

public class Listener extends ScreenshotUtility implements ITestListener {
	// Static ExtentReports object for generating test reports
		public static ExtentReports extent = ExtentReportNG.getReportObject(GlobalVariable.basepath+"//TestReport//report.html");
		// Map to store ExtentTest objects for each test class
		public static Map<String, ExtentTest> classTestMap = new HashMap<>();
		// ThreadLocal to store the current ExtentTest object
		public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

		public void onTestStart(ITestResult result) {
			// Get the class name and test method name
			String className = result.getTestClass().getName();
			String testMethodName = result.getMethod().getMethodName();
			// Get or create an ExtentTest object for the class
			ExtentTest classTest = classTestMap.computeIfAbsent(className, k -> extent.createTest(className));
			// Create a node for the test method under the class test
			ExtentTest test = classTest.createNode(testMethodName);
			extentTest.set(test);// Set the current ExtentTest object to the ThreadLocal variable
						
			// Log the start of the test
			extentTest.get().log(Status.INFO, "Starting test: "+ testMethodName); 
		}

		public void onTestSuccess(ITestResult result) {
			// Log test success
			extentTest.get().log(Status.PASS, "Test Passed!");
		}

		public void onTestFailure(ITestResult result) {
			// Get the instance of the test class and initialize WebDriver
			Object testInstance = result.getInstance();
			WebDriver driver = null;
			try {
				// Get the WebDriver instance from the test class using reflection
				Method getDriverMethod = testInstance.getClass().getMethod("getDriver"); // Use the getter method
				driver = (WebDriver) getDriverMethod.invoke(testInstance);
				// Take a screenshot and get the path
				 String screenshotPath = takeScreenshot(driver, result.getMethod().getMethodName());
				 // Log the failure and attach the screenshot
				 extentTest.get().fail(result.getThrowable());
				 extentTest.get().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
			} catch (Exception e) {
				// Log any exceptions that occur during screenshot capture
				 System.out.println("Unable to take Screenshot.");
				 e.printStackTrace();
			}
		}
		
		public void onTestSkipped(ITestResult result) {
			// Log test skip
			extentTest.get().log(Status.SKIP, "Test Skipped!");
		}

		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// Log partial success
			extentTest.get().fail(result.getThrowable());
		}

		public void onTestFailedWithTimeout(ITestResult result) {
			onTestFailure(result);
		}

		public void onStart(ITestContext context) {
			// Do any setup if necessary
		}

		public void onFinish(ITestContext context) {
			// Flush the extent reports to write the results to the report file
			extent.flush();
		}
}
