package com.mbc.receiptprinter.ui.totalyearlyamountreport;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.process.address.AddressFetchProcess;
import com.mbc.receiptprinter.process.address.AddressProcessUtil;
import com.mbc.receiptprinter.process.receipt.ReceiptFetchProcess;
import com.mbc.receiptprinter.ui.tabs.TotalYearlyAmountReportTabColumns;

import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.util.*;

public class TotalYearlyAmountReportTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	private final ReceiptFetchProcess fetchProcess = new ReceiptFetchProcess();
	private final String year;

	public TotalYearlyAmountReportTableModel(String year) {
	    this.year = year;
		setDataVector(getYearlyReportData(), TotalYearlyAmountReportTabColumns.getAllNames());
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	/**
	 * Gets the total yearly amount per address.  The returned data is used in the total yearly amount report
	 * @return A two dimensional Object array of total yearly amount data that is used for display purposes
	 */
	public Object[][] getYearlyReportData() {

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
     * Creates a TreeMap that contains the yearly report data. The TreeMap is in "receipt address - amount" format
     * For example: "Midcoast Baptist Church (170 Old Portland Rd, Brunswick, ME) - 100.00"
     * @param year The year of the data to report on
     * @return A TreeMap of yearly report data (TreeMap is used to keep the receipt addresses in alphabetical order)
     */
    private TreeMap<String, BigDecimal> createYearlyReportDataMap(String year) {
        AddressFetchProcess addressFetchProcess = new AddressFetchProcess();
        TreeMap<String, BigDecimal> dataMap = new TreeMap<String, BigDecimal>();
        for (Address address : addressFetchProcess.fetchAddresses()) {
            BigDecimal amount = new BigDecimal("0.00");
            List<Receipt> receipts = fetchProcess.fetchReceipts();
            for (Receipt receipt : receipts) {
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
    private int getUniqueReceiptAddressCount(String year) {
        Set<Address> receiptAddresses = new TreeSet<Address>();
        List<Receipt> receipts = fetchProcess.fetchReceipts();
        for (Receipt receipt : receipts) {
            if (year.equals(receipt.getReceiptDate().substring(6))) {
                receiptAddresses.add(receipt.getAddress());
            }
        }
        return receiptAddresses.size();
    }
}
