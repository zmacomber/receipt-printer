package com.mbc.receiptprinter.util;

public class ReceiptPrinterStringUtils {
	
	public static boolean isNullOrEmpty(String s) {
		if ((s == null) || (s.trim().length() == 0)) {
			return true;
		} else {
			return false;
		}	
	}

	public static boolean isNotNullOrEmpty(String s) {
		if ((s != null) && (s.trim().length() > 0)) {
			return true;
		} else {
			return false;
		}	
	}
}
