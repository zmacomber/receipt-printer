package com.mbc.receiptprinter.ui.totalyearlyamountreport;

import java.awt.Font;

import javax.swing.JButton;

import com.mbc.receiptprinter.constant.ActionCommand;
import com.mbc.receiptprinter.ui.tabs.TotalYearlyAmountReportTab;

public class TotalYearlyAmountReportPrintButton extends JButton {

	private static final long serialVersionUID = 1L;

	public TotalYearlyAmountReportPrintButton(TotalYearlyAmountReportTab totalYearlyAmountReportTab) {
		setText("Print Yearly Report");
		setActionCommand(ActionCommand.PRINT_YEARLY_REPORT);
		addActionListener(new TotalYearlyAmountReportListener(totalYearlyAmountReportTab));
		setFont(new Font("Tahoma", Font.PLAIN, 12));
	}
}
