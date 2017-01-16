package com.mbc.receiptprinter.ui.totalyearlyamountreport;

import javax.swing.JButton;

import com.mbc.receiptprinter.constant.ActionCommand;
import com.mbc.receiptprinter.ui.tabs.TotalYearlyAmountReportTab;
import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

public class TotalYearlyAmountReportPrintButton extends JButton {

	private static final long serialVersionUID = 1L;

	public TotalYearlyAmountReportPrintButton(TotalYearlyAmountReportTab totalYearlyAmountReportTab) {
		setText("Print Yearly Report");
		setActionCommand(ActionCommand.PRINT_YEARLY_REPORT);
		addActionListener(new TotalYearlyAmountReportListener(totalYearlyAmountReportTab));
		setFont(ReceiptPrinterUIUtils.getDefaultFont());
	}
}
