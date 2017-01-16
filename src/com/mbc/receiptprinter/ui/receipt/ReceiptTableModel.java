package com.mbc.receiptprinter.ui.receipt;

import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.process.address.AddressProcessUtil;
import com.mbc.receiptprinter.process.receipt.ReceiptFetchProcess;
import com.mbc.receiptprinter.ui.tabs.ReceiptTabColumns;

import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.List;

public class ReceiptTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	public ReceiptTableModel() {
		ReceiptFetchProcess fetchProcess = new ReceiptFetchProcess();
		setDataVector(getReceiptData(fetchProcess), ReceiptTabColumns.getAllNames());
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	/**
	 * Formats the Receipt records in such a way as to be able to display them in a table on the user interface
	 * @return A two dimensional Object array of Receipt data that is used for display purposes
	 * @param receiptFetchProcess
	 */
	public static Object[][] getReceiptData(ReceiptFetchProcess receiptFetchProcess) {
		List<Receipt> receipts = receiptFetchProcess.fetchReceipts();
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
}
