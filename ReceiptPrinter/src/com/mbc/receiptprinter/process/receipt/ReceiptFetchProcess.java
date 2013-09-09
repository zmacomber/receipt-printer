package com.mbc.receiptprinter.process.receipt;

import java.io.IOException;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
	
	/**
	 * Formats the Receipt records in such a way as to be able to display them in a table on the user interface
	 * @return A two dimensional Object array of Receipt data that is used for display purposes
	 */
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
	
	/**
	 * Gets the total yearly amount per address.  The returned data is used in the total yearly amount report
	 * @param year The year to calculate the total amount per address for
	 * @return A two dimensional Object array of total yearly amount data that is used for display purposes
	 */
	public Object[][] getYearlyReportData(String year) {
		
		Map<String, BigDecimal> dataMap = createYearlyReportDataMap(year);
		
		Object[][] data = new Object[getUniqueReceiptAddressCount(year)][TotalYearlyAmountReportTabColumns.values().length];
		
		int counter = 0;
		for (String key : dataMap.keySet()) {
			data[counter][TotalYearlyAmountReportTabColumns.NAME.getColumn()] = key;
			data[counter][TotalYearlyAmountReportTabColumns.AMOUNT.getColumn()] = dataMap.get(key);
			counter++;
		}
			
		return data;	
	}
	
	/**
	 * Used for populating the year address combo box on the total yearly amount report
	 * @return A String array of receipt years in descending order
	 */
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
	
	/**
	 * Creates a TreeMap that contains the yearly report data. The TreeMap is in "receipt address - amount" format
	 * For example: "Midcoast Baptist Church (170 Old Portland Rd, Brunswick, ME) - 100.00"
	 * @param year The year of the data to report on
	 * @return A TreeMap of yearly report data (TreeMap is used to keep the receipt addresses in alphabetical order)
	 */
	TreeMap<String, BigDecimal> createYearlyReportDataMap(String year) {
		AddressFetchProcess addressFetchProcess = new AddressFetchProcess();
		TreeMap<String, BigDecimal> dataMap = new TreeMap<String, BigDecimal>();
		for (Address address : addressFetchProcess.fetchAddresses()) {
			BigDecimal amount = new BigDecimal("0.00");
			for (Receipt receipt : fetchReceipts()) {
				if (address.equals(receipt.getAddress()) && receipt.getReceiptDate().substring(6).equals(year)) {
					amount = amount.add(new BigDecimal(receipt.getAmount()));
				}
			}
			if ( ! amount.toPlainString().equals("0.00")) {
				dataMap.put(AddressProcessUtil.getAddressForReceipt(address), amount);
			}
		}
		return dataMap;
	}
	
	/**
	 * Fetches the total unique addresses for a given year.
	 * @param year The year to calculate the total unique address per year for
	 * @return The total unique addresses for a given year
	 */
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