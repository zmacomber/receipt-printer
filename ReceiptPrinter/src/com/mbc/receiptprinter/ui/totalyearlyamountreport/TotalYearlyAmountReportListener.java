package com.mbc.receiptprinter.ui.totalyearlyamountreport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mbc.receiptprinter.constant.ActionCommand;
import com.mbc.receiptprinter.ui.tabs.TotalYearlyAmountReportTab;

public class TotalYearlyAmountReportListener implements ActionListener {
	
	private TotalYearlyAmountReportTab totalYearlyAmountReportTab;
	public TotalYearlyAmountReportListener(TotalYearlyAmountReportTab totalYearlyAmountReportTab) { this.totalYearlyAmountReportTab = totalYearlyAmountReportTab; }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ActionCommand.PRINT_YEARLY_REPORT)) {
			String year = totalYearlyAmountReportTab.getReceiptYears().getSelectedItem().toString();
			totalYearlyAmountReportTab.getTotalYearlyAmountReportTable().printYearlyReport(year);
		}
		if (e.getActionCommand().equals(ActionCommand.YEARLY_REPORT_SELECTED_YEAR_CHANGED)) {
			String year = totalYearlyAmountReportTab.getReceiptYears().getSelectedItem().toString();
			totalYearlyAmountReportTab.getTotalYearlyAmountReportTable().repopulate(year);
		}
	}
}
