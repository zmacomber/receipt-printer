package com.mbc.receiptprinter.process.receipt;

import java.util.List;

import com.mbc.receiptprinter.bean.Receipt;

public class ReceiptProcessUtil {

	public static boolean receiptAlreadyExists(Receipt receiptToCheck, List<Receipt> receipts) {
		if (receiptToCheck == null) return false;
		boolean receiptAlreadyExists = false;
		for (Receipt receipt : receipts) {
			if (receipt.getReceiptDate().trim().equalsIgnoreCase(receiptToCheck.getReceiptDate().trim()) &&
				receipt.getAddress().equals(receiptToCheck.getAddress()) &&
				receipt.getDesignation().equals(receiptToCheck.getDesignation()) &&
				receipt.getAmount().trim().equalsIgnoreCase(receiptToCheck.getAmount().trim())) {
				receiptAlreadyExists = true;
				break;
			}
		}
		return receiptAlreadyExists;
	}
}
