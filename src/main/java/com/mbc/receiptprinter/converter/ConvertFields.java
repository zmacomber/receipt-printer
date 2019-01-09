package com.mbc.receiptprinter.converter;

/**
* Used to convert the fields found in a data file into the bean corresponding to the data file.
* For instance, if an address data file is parsed and the fields are extracted from a record into a String array,
* an Address bean will be returned from the convert method.
*/
public interface ConvertFields<T> {

	/**
	* Converts a fields array (which represents a line in a data file) into it's corresponding bean
	* @param fields The String array that is extracted from a line in a data file through using an ASCII field delimiter
	* @return A bean that corresponds with the data file used to extract the fields from
	*/
	public T convert(String[] fields);

	/**
	* Used to indicate if the data file has multiple fields in it or not
	* @return true if the data file has multiple fields in it; false otherwise
	*/
	public boolean hasMultipleFields();
}
