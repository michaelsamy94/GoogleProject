package com.google.actiondriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.base.BaseClass;
import com.google.utility.ExtentManager;
import com.google.utility.Log;

/**
 * Action class used for managing all the webdriver actions
 * 
 * @author Michael
 *
 */
public class Action extends BaseClass {

	/**
	 * Find an element
	 * 
	 * @param elementLocater the By of the element
	 * @return The WebElement
	 */
	public WebElement findElement(By elementLocater) {

		WebElement ele = driver.findElement(elementLocater);

		return ele;

	}

	/**
	 * Click on an element
	 * 
	 * @param elementLocater the By of the element
	 */
	public void click(By elementLocater) {
		WebElement ele = findElement(elementLocater);

		try {
			ele.click();
			String msg = "Clicked on element (" + elementLocater.toString() + ")";
			Log.info(msg);
			ExtentManager.test.pass(msg);
		} catch (Exception e) {
			String error = "Unable to click on element (" + elementLocater.toString() + ")";
			Log.error(error);
			ExtentManager.test.fail(error);
		}
	}

	/**
	 * Count the number of elements found with the same By
	 * 
	 * @param elementLocater By of the element
	 * @return
	 */
	public int countElements(By elementLocater) {
		try {
			int count = driver.findElements(elementLocater).size();
			String msg = "Count number of elements (" + elementLocater.toString() + ")";
			Log.info(msg);
			ExtentManager.test.pass(msg);
			return count;

		} catch (Exception e) {
			String error = "Unable to count number of elements (" + elementLocater.toString() + ")";
			Log.error(error);
			ExtentManager.test.fail(error);
			return 0;
		}

	}

	/**
	 * Type on an element
	 * 
	 * @param elementLocater By of element
	 * @param text           text to be typed
	 */
	public void type(By elementLocater, String text) {

		WebElement ele = findElement(elementLocater);

		try {
			ele.clear();
			ele.sendKeys(text);
			String msg = "Typed text \"" + text + "\" in element " + elementLocater.toString();
			Log.info(msg);
			ExtentManager.test.pass(msg);
		} catch (Exception e) {
			String error = "Unable to type text \"" + text + "\" in element " + elementLocater.toString();
			Log.error(error);
			ExtentManager.test.fail(error);
		}

	}

	/**
	 * Scroll to an element to view
	 * 
	 * @param elementLocater By of an element
	 */
	public void scrollToElement(By elementLocater) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement ele = findElement(elementLocater);

		// This will scroll the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", ele);

	}

	public String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}

		return destination;
	}

}
