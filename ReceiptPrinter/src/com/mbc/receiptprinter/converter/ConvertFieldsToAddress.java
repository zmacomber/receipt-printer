package com.mbc.receiptprinter.converter;

import com.mbc.receiptprinter.bean.Address;

/**
* Converts fields found in a line from an Address data file into an Address bean
* @see ConvertFields
*/
public class ConvertFieldsToAddress implements ConvertFields<Address> {

	/**
	* @throws IllegalArgumentException If fields is null or not the correct length
	*/
	public Address convert(String[] fields) {
		int fieldLength = 7; 
		if ((fields == null) || (fields.length != fieldLength)) {
			throw new IllegalArgumentException("Fields param (" + fields + ") must not be null and have a length of " + fieldLength); 
		}
		return Address.newInstance(Long.valueOf(fields[0]),
					   			   fields[1], 
					   			   fields[2],
					   			   fields[3],
					   			   fields[4],
					   			   fields[5],
					   			   fields[6]);
	}
	
	public boolean hasMultipleFields() { return true; }
}
