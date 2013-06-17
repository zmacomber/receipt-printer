package com.mbc.receiptprinter.constant;

/**
* Contains the full file paths of the data, log and properties files used in the application.
* These paths have as a root directory the user's home followed by "ReceiptPrinter".
*/
public class FilePaths {
	
	public static final String USER_HOME = System.getProperty("user.home");
	
	public static final String RECEIPT_DATA_PATH 	 = USER_HOME + "/ReceiptPrinter/data/receipt.dat";
	public static final String ADDRESS_DATA_PATH 	 = USER_HOME + "/ReceiptPrinter/data/address.dat";
	public static final String DESIGNATION_DATA_PATH = USER_HOME + "/ReceiptPrinter/data/designation.dat";
	
	public static final String LOG_PATH		 		 = USER_HOME + "/ReceiptPrinter/logs/log.txt";
	
	public static final String APPLICATION_PROPERTIES_PATH = USER_HOME + "/ReceiptPrinter/Application.properties";
}
