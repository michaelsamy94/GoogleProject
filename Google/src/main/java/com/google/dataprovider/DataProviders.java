package com.google.dataprovider;

import org.testng.annotations.DataProvider;

import com.google.utility.ExcelLibrary;

/**
 * 
 * @author Michael
 *
 */
public class DataProviders {

	ExcelLibrary obj = new ExcelLibrary(
			System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TestData.xlsx");

	/**
	 * Setting the data providor for the searchKeywords from the excel
	 * 
	 * @return The 2D array of data
	 */
	@DataProvider(name = "searchKeywords")
	public Object[][] getSearchKeywords() {
		// Totals rows count
		int rows = obj.getRowCount("SearchKeywords");
		// Total Columns
		int column = obj.getColumnCount("SearchKeywords");
		int actRows = rows - 1;

		Object[][] data = new Object[actRows][column];

		for (int i = 0; i < actRows; i++) {
			for (int j = 0; j < column; j++) {
				data[i][j] = obj.getCellData("SearchKeywords", j, i + 2);
			}
		}
		return data;
	}

}
