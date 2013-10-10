package com.mbc.receiptprinter.converter;

import com.mbc.receiptprinter.bean.Designation;

/**
* Converts fields found in a line from a Designation data file into a Designation bean
* @see ConvertFields
*/
public class ConvertFieldsToDesignation implements ConvertFields<Designation> {

	/**
	* @throws IllegalArgumentException If fields is null or not the correct length
	*/
	public Designation convert(String[] fields) {
		if ((fields == null) || (fields.length != 1)) {
			throw new IllegalArgumentException("Fields param (" + fields + ") must not be null and have a length of 1"); 
		}
		return Designation.newInstance(fields[0]);
	}
	public boolean hasMultipleFields() { return false; }
}
