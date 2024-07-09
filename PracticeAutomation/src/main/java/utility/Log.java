package utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class Log {
private static Logger log = LogManager.getLogger();
	
	public static synchronized void startTestCase(String testCaseName) {
		testCaseName = testCaseName.replaceAll("[^a-zA-Z0-9]", "_").replaceAll("_+", "_");
		ThreadContext.put("logFilename", testCaseName);
		info("*****************************************************************************************");
		info("");
		info("\t\t\t--{  "+testCaseName.toUpperCase()+" - STARTS  }--");
		info("");
		info("*****************************************************************************************");
	}

	public static String getCallInfo() {
		String callInfo;
		String classname 	= 	Thread.currentThread().getStackTrace()[3].getClassName();
		String methodname 	=	Thread.currentThread().getStackTrace()[3].getMethodName();
		int linnumber		=	Thread.currentThread().getStackTrace()[3].getLineNumber();
			
		callInfo = classname+ "." + methodname+ "." + linnumber + "-";
		return callInfo;
	}

	public static Logger getCurrentLog() {
		return log;
	}
	
	public static void trace(Object message) {
		getCurrentLog().trace(message);
	}

	public static void trace(Object message, Throwable t) {
		getCurrentLog().trace(message, t);
	}

	public static void debug(Object message) {
		getCurrentLog().debug(getCallInfo() + message);
	}

	public static void debug(Object message, Throwable t) {
		getCurrentLog().debug(getCallInfo() + message, t);
	}

	public static void error(Object message) {
		getCurrentLog().error(getCallInfo() + message);
	}

	public static void error(Object message, Throwable t) {
		getCurrentLog().error(getCallInfo() + message, t);
	}

	public static void fatal(Object message) {
		getCurrentLog().fatal(getCallInfo() + message);
	}

	public static void fatal(Object message, Throwable t) {
		getCurrentLog().fatal(getCallInfo() + message, t);
	}

	public static void info(Object message) {
		getCurrentLog().info(getCallInfo() + message);
	}

	public static void info(Object message, Throwable t) {
		getCurrentLog().info(getCallInfo() + message, t);
	}

	public static void warn(Object message) {
		getCurrentLog().warn(getCallInfo() + message);
	}

	public static void warn(Object message, Throwable t) {
		getCurrentLog().warn(getCallInfo() + message, t);
	}
}
