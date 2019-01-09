package com.mbc.receiptprinter.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReceiptPrinterNumberUtils {

	public static final Pattern ZERO_DOLLAR_AMOUNT_PATTERN 	= Pattern.compile("^[0].[0-9][0-9]$");
	public static final Pattern AMOUNT_PATTERN 				= Pattern.compile("^[1-9]([0-9])*.[0-9][0-9]$"); 
	
	public static boolean isAmountInvalid(String amount) {
		if (ReceiptPrinterStringUtils.isNullOrEmpty(amount)) return true;
		
		Matcher m = null;
		
		// Zero dollar amount (i.e. "0.43")
		if ((amount.length() > 2) && (amount.charAt(0) == '0') && (amount.charAt(1) == '.')) {
			m = ZERO_DOLLAR_AMOUNT_PATTERN.matcher(amount);
		} else {
			m = AMOUNT_PATTERN.matcher(amount);
		}
		
		if ((m != null) && (m.find() == true)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static BigDecimal getBigDecimal(String number) {
		try {
			return new BigDecimal(number);
		} catch (Exception e) {
			return new BigDecimal("0.00");
		}
	}
}
