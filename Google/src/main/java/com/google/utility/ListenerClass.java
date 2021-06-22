package com.google.utility;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.google.actiondriver.Action;
import com.google.base.BaseClass;

/**
 * 
 * @author Michael
 *
 */
public class ListenerClass extends ExtentManager implements ITestListener {

	Action action = new Action();

	/**
	 * On test start create a test instance for extent report
	 */
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
	}

	/**
	 * On test success log success in the extent test
	 */
	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Pass Test case is: " + result.getName());
		}
	}

	/**
	 * On test failure log failure and attach screenshot in the report
	 */

	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			String imgPath = action.screenShot(BaseClass.driver, result.getName());

			test.fail("ScreenShot is Attached", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());

		}
	}

	/**
	 * On test skipped log a skip in extent test
	 */
	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Skipped Test case is: " + result.getName());
		}
	}

}
