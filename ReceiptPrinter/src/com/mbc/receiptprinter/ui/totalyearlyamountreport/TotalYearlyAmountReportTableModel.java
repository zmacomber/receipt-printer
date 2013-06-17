package com.mbc.receiptprinter.ui.totalyearlyamountreport;

import javax.swing.table.DefaultTableModel;

import com.mbc.receiptprinter.process.receipt.ReceiptFetchProcess;
import com.mbc.receiptprinter.ui.tabs.TotalYearlyAmountReportTabColumns;

public class TotalYearlyAmountReportTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	public TotalYearlyAmountReportTableModel(String year) {
		ReceiptFetchProcess fetchProcess = new ReceiptFetchProcess();
		setDataVector(fetchProcess.getYearlyReportData(year), TotalYearlyAmountReportTabColumns.getAllNames());
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
