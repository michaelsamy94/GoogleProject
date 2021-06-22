package com.google.testcases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.base.BaseClass;
import com.google.dataprovider.DataProviders;
import com.google.pageobjects.HomePage;
import com.google.pageobjects.SearchResultsPage;
import com.google.utility.ExtentManager;
import com.google.utility.HelperMethods;
import com.google.utility.Log;

/**
 * 
 * @author Michael
 *
 */
public class SearchTests extends BaseClass {

	/**
	 * Launch the app
	 */
	@BeforeMethod
	public void setup() {
		launchApp();
	}

	/**
	 * Quit the driver
	 */
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	/**
	 * Search Test Case : search for a keyword an validate the that all search
	 * results page have the same number of results
	 * 
	 * @param searchKeyword
	 */
	@Test(dataProvider = "searchKeywords", dataProviderClass = DataProviders.class)
	public void searchTest(String searchKeyword) {

		Log.startTestCase("Search Test Case");

		HomePage homePage = new HomePage();

		ExtentManager.test.info("Searching for \"" + searchKeyword + "\"");
		SearchResultsPage firstPage = homePage.searchText(searchKeyword);

		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < 2; i++) {
			SearchResultsPage resultPage = firstPage.clickNext();
			int searchCount = resultPage.countSearchResults();
			list.add(searchCount);
			ExtentManager.test.info("Number of search Result in page " + i + " equals " + searchCount);
		}

		Assert.assertTrue(HelperMethods.ListAllEqual(list));
		ExtentManager.test.pass("Number of search Results in pages are equal");

	}

	/**
	 * A forced failure scenario
	 */
	@Test
	public void searchTestFailure() {

		String searchKeyword = "Test Automation";
		Log.startTestCase("Search Test Case");
		HomePage homePage = new HomePage();
		SearchResultsPage firstPage = homePage.searchText(searchKeyword);
		Log.info("Searching for " + searchKeyword);

		SearchResultsPage secondPage = firstPage.clickNext();
		Assert.assertEquals(secondPage.countSearchResults(), 5);

	}

}
