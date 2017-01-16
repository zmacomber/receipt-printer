package com.mbc.receiptprinter.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Various Date utility functions
 */
public class ReceiptPrinterDateUtils {
	
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	static { DATE_FORMAT.setLenient(false); }

	public static boolean isDateInvalid(String dateToCheck) {
		if (ReceiptPrinterStringUtils.isNullOrEmpty(dateToCheck)) return true;
		try {
			DATE_FORMAT.parse(dateToCheck);
		} catch (ParseException e) {
			return true;
		}
		return false;
	}
	
	public static Date getDate(String dateToConvert) {
		try {
			return DATE_FORMAT.parse(dateToConvert);
		} catch (ParseException e) {
			return new Date();
		}
	}
	
	public static String getTodaysFormattedDate() {
		return DATE_FORMAT.format(new Date());
	}
}
