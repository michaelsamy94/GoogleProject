package com.google.utility;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	/**
	 * Setting the extent report using the config xml and setting the HTML report
	 * path, setting basic system info to be displayed in the report
	 */
	public static void setExtent() {

		htmlReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/test-output/ExtentReport/" + "MyReport.html");
		try {
			htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("ProjectName", "MyGoogleProject");
		extent.setSystemInfo("Tester", "Michael");
		extent.setSystemInfo("OS", "Win10");
	}

	/**
	 * Flushing the report to be generated
	 */
	public static void endReport() {
		extent.flush();
	}
}
