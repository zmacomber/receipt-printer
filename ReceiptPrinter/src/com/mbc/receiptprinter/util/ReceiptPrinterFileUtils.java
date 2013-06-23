package com.mbc.receiptprinter.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.mbc.receiptprinter.constant.FileDelimiters;

/*
 * Various File utility functions.  This class extends the Apache Commons FileUtils class.
 */
public class ReceiptPrinterFileUtils extends FileUtils {
	
	public static String getFileContents(String filePath) throws IOException {
		if (ReceiptPrinterStringUtils.isNullOrEmpty(filePath)) throw new IllegalArgumentException("File path must not be null or empty");
		File f = new File(filePath);
		touch(f);
		return readFileToString(f);
	}
	
	public static List<String> convertFileContentsToList(String fileContents) {
		List<String> records = new ArrayList<String>();
		if(fileContents.contains(FileDelimiters.RECORD)) {
			records = Arrays.asList(fileContents.split(FileDelimiters.RECORD));
		}
		return records;
	}

}
