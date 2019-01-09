package com.mbc.receiptprinter.dao;

import com.mbc.receiptprinter.constant.FileDelimiters;
import com.mbc.receiptprinter.converter.ConvertToStringRecord;
import com.mbc.receiptprinter.util.ReceiptPrinterFileUtils;

import java.io.IOException;

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
		ReceiptPrinterFileUtils.writeStringToFile(dataFilePath, convertToStringRecord.build() + FileDelimiters.RECORD, true);
	}
}
