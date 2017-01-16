package com.mbc.receiptprinter.process.receipt;

import java.util.List;

import com.mbc.receiptprinter.bean.Receipt;

/**
 * Various helper methods used in the processing of Receipt records
 */
public class ReceiptProcessUtil {

	/**
	 * Checks to see if a Receipt already exists
	 * @param receiptToCheck The Receipt to verify
	 * @param receipts The List of Receipt records to scan for the receiptToCheck
	 * @return true if the receiptToCheck is found in the receipts List; false otherwise
	 */
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
