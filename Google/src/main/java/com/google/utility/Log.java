package com.google.utility;

import org.apache.log4j.Logger;

/**
 * 
 * @author Michael
 *
 */
public class Log {

	// Initialize Log4j logs
	public static Logger Log = Logger.getLogger(Log.class.getName());

	/**
	 * Log start of a testcase
	 * 
	 * @param testCaseName
	 */
	public static void startTestCase(String testCaseName) {
		Log.info("=====================================" + testCaseName
				+ " TEST START=========================================");
	}

	/**
	 * Log end of a testcase
	 * 
	 * @param testCaseName
	 */
	public static void endTestCase(String testCaseName) {
		Log.info("=====================================" + testCaseName
				+ " TEST END=========================================");
	}

	/**
	 * Log info message
	 * 
	 * @param message
	 */
	public static void info(String message) {

		Log.info(message);

	}

	/**
	 * Log warning message
	 * 
	 * @param message
	 */
	public static void warn(String message) {

		Log.warn(message);

	}

	/**
	 * Log error message
	 * 
	 * @param message
	 */
	public static void error(String message) {

		Log.error(message);
	}

}
