package com.mbc.receiptprinter.converter;


/**
 * Used to convert a data bean (i.e. Address) into a String for the purpose of deleting and/or appending
 * into a data file
 * @param <T> The type of the data bean (i.e. Address)
 */
public interface ConvertToStringRecord<T> {

	/**
	 * Builds a String record for use in appending/deleting inside a data file
	 * @return A String record formatted for appending/deleting to a data file
	 */
	public String build();

}
