package com.google.utility;

import java.util.ArrayList;

/**
 * 
 * @author Michael
 *
 */
public class HelperMethods {

	/**
	 * Validate if all items in a list are equal
	 * 
	 * @param list array list of integers
	 * @return true if all items are equal
	 */
	public static boolean ListAllEqual(ArrayList<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).equals(list.get(0)))
				return false;
		}
		return true;
	}

}
