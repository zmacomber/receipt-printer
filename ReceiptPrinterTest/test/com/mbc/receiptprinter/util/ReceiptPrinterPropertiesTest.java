package com.mbc.receiptprinter.util;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class ReceiptPrinterPropertiesTest {
	
	// Adjust these for wherever the ReceiptPrinter src directory is on your machine
	private static final String RECEIPT_PRINTER_SOURCE_DIR = "C:/eclipse_projects/ReceiptPrinter/src";
	
	// This is the token that a line in a file has a call to get a Property
	private static final String PROPERTY_SEARCH_STRING = "ReceiptPrinterProperties.getProperty";
	
	/*
	 * Verify all properties in the application have a value by
	 * recursively going through all of the files under the ReceiptPrinter src
	 * directory and searching for PROPERTY_SEARCH_STRING.
	 * Once those entries are found, extract the String value between the
	 * parentheses as a String and then make the actual call to ensure the
	 * property key exists
	 */
	@Test
	public void testGetProperty() throws Exception {
		
		// Iterator for all files in the RECEIPT_PRINTER_SOURCE_DIR and all of it's sub-directories
		Iterator<File> receiptPrinterSourceFiles = ReceiptPrinterFileUtils.iterateFiles(new File(RECEIPT_PRINTER_SOURCE_DIR), null, true);
		
		while(receiptPrinterSourceFiles.hasNext()) {
			List<String> fileLines = ReceiptPrinterFileUtils.readLines(receiptPrinterSourceFiles.next());
			for (String line : fileLines) {
				if (line.contains(PROPERTY_SEARCH_STRING)) {
					int startIndex = line.indexOf("(", line.indexOf(PROPERTY_SEARCH_STRING)) + 2;
					int endIndex   = line.indexOf("\"", startIndex);
					String key = line.substring(startIndex, endIndex);
					assertNotNull("Property '" + key + "' could not be found", ReceiptPrinterProperties.getProperty(key));
				}
			}
		}
	}

}
