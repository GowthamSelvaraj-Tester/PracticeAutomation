package report;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	// Declare a static ExtentReports object
	public static ExtentReports extent;
	// Method to get a synchronized instance of ExtentReports
	public static synchronized ExtentReports getReportObject(String reportPath) {
		if (extent == null) { // Check if the ExtentReports object is null
			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);// Create a new ExtentSparkReporter with the specified report path
			reporter.config().setReportName("JustTip Automation Result");// Set the report name in the configuration
			reporter.config().setDocumentTitle("Test Results");// Set the document title in the configuration
								
			extent = new ExtentReports(); // Create a new ExtentReports object and attach the reporter
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester", System.getProperty("user.name"));// Set system information in the report
			try {
				extent.setSystemInfo("Machine", InetAddress.getLocalHost().getHostName()); // Set the machine name in the system information
			} catch (UnknownHostException e) {
				extent.setSystemInfo("Machine", "Unknown Host");// If unable to capture hostname, set as "Unknown Host"
				System.out.println("Unable to capture hostname.");
			}
				
			// Set Java version in the system information
			String javaVersion = System.getProperty("java.version");
			extent.setSystemInfo("Java Version", javaVersion);

			// Set Operating System name and version in the system information
			String osName = System.getProperty("os.name");
			String osVersion = System.getProperty("os.version");
			extent.setSystemInfo("Operating System", osName + " " + osVersion);
				
			// Set Environment variable in the system information
			String env = System.getenv("TEST_ENV");
			if (env != null) {
				extent.setSystemInfo("Environment", env);
			} else {
				extent.setSystemInfo("Environment", "Not Set");
			}
		}
		return extent; // Return the ExtentReports object
	}
}
