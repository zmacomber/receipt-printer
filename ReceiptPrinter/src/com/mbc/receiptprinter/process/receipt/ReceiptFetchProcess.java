package com.mbc.receiptprinter.process.receipt;

import java.io.IOException;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.TreeSet;
import java.util.Set;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.converter.ConvertFieldsToReceipt;
import com.mbc.receiptprinter.dao.FetchDao;
import com.mbc.receiptprinter.process.address.AddressFetchProcess;
import com.mbc.receiptprinter.process.address.AddressProcessUtil;
import com.mbc.receiptprinter.ui.tabs.ReceiptTabColumns;
import com.mbc.receiptprinter.ui.tabs.TotalYearlyAmountReportTabColumns;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;
import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

public class ReceiptFetchProcess {
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
	public List<Receipt> fetchReceipts() {
		FetchDao<Receipt> fetchDao = new FetchDao<Receipt>();
		List<Receipt> receipts = new ArrayList<Receipt>();
		try {
			receipts = fetchDao.fetchAll(FilePaths.RECEIPT_DATA_PATH, new ConvertFieldsToReceipt());
		} catch (IOException e) {
			ReceiptPrinterLogger.logMessage(this.getClass(), Level.SEVERE, "IOException while fetching receipts", e);
		}
		return receipts;
	}
	public Object[][] getReceiptData() {
		List<Receipt> receipts = fetchReceipts();
		Collections.sort(receipts);
		Object[][] data = new Object[receipts.size()][ReceiptTabColumns.values().length];
		int counter = 0;
		for (Receipt receipt : receipts) {
			data[counter][ReceiptTabColumns.RECEIPT_DATE.getColumn()] = receipt.getReceiptDate();
			data[counter][ReceiptTabColumns.ADDRESS.getColumn()] = AddressProcessUtil.getAddressForReceipt(receipt.getAddress());
			data[counter][ReceiptTabColumns.DESIGNATION.getColumn()] = receipt.getDesignation().getName();
			data[counter][ReceiptTabColumns.AMOUNT.getColumn()] = receipt.getAmount();
			data[counter][ReceiptTabColumns.NOTES.getColumn()] = receipt.getNotes();
			counter++;
		}
		return data;
	}
	public Object[][] getYearlyReportData(String year) {
		List<Receipt> receipts = fetchReceipts();
		AddressFetchProcess addressFetchProcess = new AddressFetchProcess();
		Collections.sort(receipts);
		Object[][] data = new Object[getUniqueReceiptAddressCount(year)][TotalYearlyAmountReportTabColumns.values().length];
		int counter = 0;
		for (Address address : addressFetchProcess.fetchAddresses()) {
			BigDecimal amount = new BigDecimal("0.00");
			for (Receipt receipt : receipts) {
				if (address.equals(receipt.getAddress()) && receipt.getReceiptDate().substring(6).equals(year)) {
					amount = amount.add(new BigDecimal(receipt.getAmount()));
				}
			}
			if ( ! amount.toPlainString().equals("0.00")) {
				data[counter][TotalYearlyAmountReportTabColumns.NAME.getColumn()] = AddressProcessUtil.getAddressForReceipt(address);
				data[counter][TotalYearlyAmountReportTabColumns.AMOUNT.getColumn()] = amount;
				counter++;	
			}
		}
		return data;	
	}
	public String[] getReceiptYears() {
		Set<String> receiptYears = new TreeSet<String>(
			new Comparator<String>() {
				// Sort descending with newest year at the top
				public int compare(String s1, String s2) {
					return Integer.valueOf(s2) - Integer.valueOf(s1);
				}
			}
		);
		for (Receipt receipt : fetchReceipts()) {
			receiptYears.add(receipt.getReceiptDate().substring(6)); // Dates are in MM/DD/YYYY format so the year starts at index 6
		}
		if (receiptYears.size() == 0) {
			return new String[] { "" };
		} else {
			return receiptYears.toArray(new String[0]);
		}
	}
	int getUniqueReceiptAddressCount(String year) {
		Set<Address> receiptAddresses = new TreeSet<Address>();
		for (Receipt receipt : fetchReceipts()) {
			if (year.equals(receipt.getReceiptDate().substring(6))) {
				receiptAddresses.add(receipt.getAddress());
			}
		}
		return receiptAddresses.size();	
	}
}