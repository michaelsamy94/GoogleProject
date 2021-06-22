package com.google.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.google.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Michael
 *
 */
public class BaseClass {

	public static Properties prop;
	public static WebDriver driver;

	/**
	 * Before suite method setting up the report, logs, config file
	 */
	@BeforeSuite
	public void loadConfig() {
		ExtentManager.setExtent();

		DOMConfigurator.configure("log4j.xml");

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ExtentManager.extent.setSystemInfo("Browser", prop.getProperty("browser"));
	}

	/**
	 * Launching the app and setting up the driver
	 */
	public void launchApp() {

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.disableNativeEvents();
			driver = new InternetExplorerDriver(ieOptions);

		}

		driver.manage().window().maximize();
		// Implicit TimeOuts
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicitWait")), TimeUnit.SECONDS);
		// PageLoad TimeOuts
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageLoadTimeOut")),
				TimeUnit.SECONDS);
		// Launching the URL
		driver.get(prop.getProperty("url"));
	}

	/**
	 * Flushing the suite report
	 */
	@AfterSuite
	public void afterSuite() {

		ExtentManager.endReport();
	}

}
