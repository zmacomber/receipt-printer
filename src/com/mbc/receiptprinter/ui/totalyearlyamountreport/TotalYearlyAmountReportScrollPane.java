package com.mbc.receiptprinter.ui.totalyearlyamountreport;

import javax.swing.JScrollPane;

public class TotalYearlyAmountReportScrollPane extends JScrollPane {

	private static final long serialVersionUID = 1L;

	public TotalYearlyAmountReportScrollPane(TotalYearlyAmountReportTable totalYearlyAmountReportTable) {
		setViewportView(totalYearlyAmountReportTable);
		setToolTipText("");
	}
}
