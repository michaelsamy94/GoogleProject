package com.google.utility;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectRepositoryParser {

	private FileInputStream stream;
	private String RepositoryFile;
	private Properties propertyFile = new Properties();

	public ObjectRepositoryParser(String fileName) {
		this.RepositoryFile = fileName;

		try {
			stream = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\ObjectRepository\\" + RepositoryFile);
			propertyFile.load(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Return the find By of the element
	 * 
	 * @param locatorName
	 * @return
	 */
	public By getObjectLocator(String locatorName) {
		String locatorProperty = propertyFile.getProperty(locatorName);

		String locatorType = locatorProperty.split(":")[0];
		String locatorValue = locatorProperty.split(":")[1];

		By locator = null;
		switch (locatorType) {
		case "Id":
			locator = By.id(locatorValue);
			break;
		case "Name":
			locator = By.name(locatorValue);
			break;
		case "CssSelector":
			locator = By.cssSelector(locatorValue);
			break;
		case "LinkText":
			locator = By.linkText(locatorValue);
			break;
		case "PartialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;
		case "TagName":
			locator = By.tagName(locatorValue);
			break;
		case "Xpath":
			locator = By.xpath(locatorValue);
			break;
		}
		return locator;
	}
}
