package com.mbc.receiptprinter.process.receipt;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.converter.ConvertFieldsToReceipt;
import com.mbc.receiptprinter.dao.FetchDao;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;
import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;

/**
 * Responsible for the fetching of Receipts
 */
public class ReceiptFetchProcess {
	
	/**
	 * Fetches a Receipt by it's key fields.  The combination of receiptDate, address, designation and amount must
	 * be unique in the application
	 * @param receiptDate The Receipt date
	 * @param address The Address associated with this Receipt
	 * @param designation The Designation associated with this Receipt
	 * @param amount The amount of the Receipt
	 * @return The Receipt that contains the given params; otherwise null
	 */
	public Receipt fetchReceipt(String receiptDate, Address address, Designation designation, String amount) {
		if (ReceiptPrinterStringUtils.isNullOrEmpty(receiptDate) ||
			(address == null) ||
			(designation == null) ||
			ReceiptPrinterStringUtils.isNullOrEmpty(amount)) return null;
		Receipt fetched = null;
		for (Receipt receipt : fetchReceipts()) {
			if (receipt.getReceiptDate().equals(receiptDate) &&
				receipt.getAddress().equals(address) &&
				receipt.getDesignation().equals(designation) &&
				receipt.getAmount().equals(amount)) {
				fetched = receipt;
				break;
			}
		}
		return fetched;
	}
	
	/**
	 * Fetches all Receipt records
	 * @return A List of all of the Receipt records in the Receipt data file; otherwise an empty list 
	 */
	public List<Receipt> fetchReceipts() {
		FetchDao<Receipt> fetchDao = new FetchDao<Receipt>();
		List<Receipt> receipts = new ArrayList<Receipt>();
		try {
			receipts = fetchDao.fetchAll(FilePaths.RECEIPT_DATA.getPath(), new ConvertFieldsToReceipt());
		} catch (IOException e) {
			ReceiptPrinterLogger.logMessage(this.getClass(), Level.SEVERE, "IOException while fetching receipts", e);
		}
		return receipts;
	}
}