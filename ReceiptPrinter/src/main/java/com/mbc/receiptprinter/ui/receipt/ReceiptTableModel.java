package com.mbc.receiptprinter.ui.receipt;

import javax.swing.table.DefaultTableModel;

import com.mbc.receiptprinter.process.receipt.ReceiptFetchProcess;
import com.mbc.receiptprinter.ui.tabs.ReceiptTabColumns;

public class ReceiptTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	public ReceiptTableModel() {
		ReceiptFetchProcess fetchProcess = new ReceiptFetchProcess();
		setDataVector(fetchProcess.getReceiptData(), ReceiptTabColumns.getAllNames());
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
