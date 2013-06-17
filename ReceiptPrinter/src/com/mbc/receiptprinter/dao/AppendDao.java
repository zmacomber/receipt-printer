package com.mbc.receiptprinter.dao;

import java.io.File;
import java.io.IOException;

import com.mbc.receiptprinter.constant.FileDelimiters;
import com.mbc.receiptprinter.converter.ConvertToStringRecord;
import com.mbc.receiptprinter.util.ReceiptPrinterFileUtils;

/**
 * Appends a record to the specified data file
 */
public class AppendDao {

	/**
	 * Appends a record to the specified data file
	 * @param dataFilePath The file path of the data file
	 * @param convertToStringRecord The String representation of the data bean record
	 * @throws IOException Possibly thrown when writeStringToFile is called
	 */
	public <T> void append(String dataFilePath, ConvertToStringRecord<T> convertToStringRecord) throws IOException {
		File dataFile = new File(dataFilePath);
		ReceiptPrinterFileUtils.writeStringToFile(dataFile, convertToStringRecord.build() + FileDelimiters.RECORD, true);
	}
}
