package com.google.pageobjects;

import com.google.actiondriver.Action;
import com.google.base.BaseClass;
import com.google.utility.ObjectRepositoryParser;

public class SearchResultsPage extends BaseClass {

	Action action = new Action();
	private ObjectRepositoryParser parser;

	private String nextLink = "nextLink";
	private String searchResults = "searchResults";

	/**
	 * The SearchResultsPage constructor setting the object repository parser
	 */
	public SearchResultsPage() {
		parser = new ObjectRepositoryParser("SearchResults.properties");
	}

	/**
	 * Click on the next link
	 * 
	 * @return the next SearchResultPage
	 */
	public SearchResultsPage clickNext() {
		action.scrollToElement(parser.getObjectLocator(nextLink));
		action.click(parser.getObjectLocator(nextLink));
		return new SearchResultsPage();

	}

	/**
	 * Count the number of search results in a page
	 * 
	 * @return integer of the total count
	 */
	public int countSearchResults() {
		return action.countElements(parser.getObjectLocator(searchResults));

	}

}
