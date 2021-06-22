package com.google.pageobjects;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.google.actiondriver.Action;
import com.google.base.BaseClass;
import com.google.utility.ExtentManager;
import com.google.utility.ObjectRepositoryParser;

/**
 * 
 * @author Michael
 *
 */
public class HomePage extends BaseClass {

	Action action = new Action();
	private ObjectRepositoryParser parser;

	private String searchField = "searchField";
	private String searchBtn = "searchBtn";

	/**
	 * Homepage constructor setting the object repo parser
	 */
	public HomePage() {

		parser = new ObjectRepositoryParser("HomePage.properties");

	}

	/**
	 * Searching for a keyword in the search field of google and clicking search
	 * 
	 * @param searchText the search keyword
	 * @return
	 */
	public SearchResultsPage searchText(String searchText) {

		action.type(parser.getObjectLocator(searchField), searchText);

		String imgPath = action.screenShot(BaseClass.driver, "Search");

		ExtentManager.test.info("ScreenShot is Attached",
				MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());

		action.click(parser.getObjectLocator(searchField));
		action.click(parser.getObjectLocator(searchBtn));

		return new SearchResultsPage();
	}

}
