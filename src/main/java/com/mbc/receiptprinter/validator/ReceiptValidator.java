package com.mbc.receiptprinter.validator;

import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.ui.tabs.ReceiptTabColumns;
import com.mbc.receiptprinter.util.ReceiptPrinterDateUtils;
import com.mbc.receiptprinter.util.ReceiptPrinterNumberUtils;
import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

public class ReceiptValidator {

	public static boolean receiptIsInvalid(Receipt receipt) {
		if (receipt == null) return true;
		if (ReceiptPrinterDateUtils.isDateInvalid(receipt.getReceiptDate()) ||
			(receipt.getAddress() == null) ||
			(receipt.getDesignation() == null) ||
			ReceiptPrinterNumberUtils.isAmountInvalid(receipt.getAmount()) ||
			receipt.getNotes() == null) {
			return true;
		}
		return false;
	}
	
	public static boolean fieldsAreInvalid(String[] fields) {
		if ((fields == null) || (fields.length != ReceiptTabColumns.values().length)) return true;
		if (ReceiptPrinterDateUtils.isDateInvalid(fields[ReceiptTabColumns.RECEIPT_DATE.getColumn()]) ||
			ReceiptPrinterStringUtils.isNullOrEmpty(fields[ReceiptTabColumns.ADDRESS.getColumn()]) ||
			ReceiptPrinterStringUtils.isNullOrEmpty(fields[ReceiptTabColumns.DESIGNATION.getColumn()]) ||
			ReceiptPrinterNumberUtils.isAmountInvalid(fields[ReceiptTabColumns.AMOUNT.getColumn()]) ||
			fields[ReceiptTabColumns.NOTES.getColumn()] == null) {
			return true;
		}
		return false;
	}
}
