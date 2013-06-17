package com.mbc.receiptprinter.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;

import com.mbc.receiptprinter.constant.FileDelimiters;
import com.mbc.receiptprinter.util.ReceiptPrinterFileUtils;

/**
 * Responsible for deleting data from a data file
 */
public class DeleteDao {
	
	/**
	 * Deletes a record from a data file
	 * @param dataFilePath The file path of the data file
	 * @param recordToDelete The record in the file to delete.  If there are duplicate records (there shouldn't be),
	 * only the first record will be deleted
	 * @throws IOException Possibly thrown when getFileContents or writeStringToFile are called
	 */
	public void delete(String dataFilePath, String recordToDelete) throws IOException {
		boolean recordDeleted = false;
		String fileContents = ReceiptPrinterFileUtils.getFileContents(dataFilePath);
		List<String> records = ReceiptPrinterFileUtils.convertFileContentsToList(fileContents);
		for (String record : records) {
			if (record.equals(recordToDelete)) {
				fileContents = fileContents.replaceFirst(Matcher.quoteReplacement(record + FileDelimiters.RECORD), "");
				recordDeleted = true;
				break;
			}
		}
		if (recordDeleted) {
			File dataFile = new File(dataFilePath);
			ReceiptPrinterFileUtils.writeStringToFile(dataFile, fileContents);
		}
	}
}
