package com.mbc.receiptprinter.constant;

/**
* Contains the full file paths of the data, log and properties files used in the application.
* These paths have as a root directory the user's home followed by "ReceiptPrinter".
*/
public enum FilePaths {
	
	RECEIPT_DATA("/ReceiptPrinter/data/receipt.dat"),
	ADDRESS_DATA("/ReceiptPrinter/data/address.dat"),
	DESIGNATION_DATA("/ReceiptPrinter/data/designation.dat"),
	LOG("/ReceiptPrinter/logs/log.txt"),
	APPLICATION_PROPERTIES("/ReceiptPrinter/Application.properties");
	
	private static final String USER_HOME = System.getProperty("user.home");
	
	private final String relativePath;
	
	FilePaths(String relativePath) {
		this.relativePath = relativePath;
	}
	
	public String getPath() {
		return USER_HOME + relativePath;
	}
	
}
