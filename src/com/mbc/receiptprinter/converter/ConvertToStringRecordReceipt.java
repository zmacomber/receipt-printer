package com.mbc.receiptprinter.converter;

import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.constant.FileDelimiters;
import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

/**
 * Converts a Receipt into a String record for use in appending/deleting in a Receipt data file.
 * @see ConvertToStringRecord
 */
public class ConvertToStringRecordReceipt implements ConvertToStringRecord<Receipt> {

	private Receipt receipt;
	
	public ConvertToStringRecordReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	@Override
	public String build() {
		StringBuilder data = new StringBuilder();
		data.append(receipt.getReceiptDate());
		data.append(FileDelimiters.FIELD);
		data.append(receipt.getAddress().getId());
		data.append(FileDelimiters.FIELD);
		data.append(receipt.getDesignation().getName());
		data.append(FileDelimiters.FIELD);
		data.append(receipt.getAmount());
		data.append(FileDelimiters.FIELD);
		data.append(ReceiptPrinterStringUtils.isNullOrEmpty(receipt.getNotes()) ? " " : receipt.getNotes());
		return data.toString();
	}

}
