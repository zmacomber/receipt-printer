package com.mbc.receiptprinter.ui.tabs;

import java.awt.GridBagConstraints;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.mbc.receiptprinter.constant.ActionCommand;
import com.mbc.receiptprinter.process.receipt.ReceiptFetchProcess;
import com.mbc.receiptprinter.ui.ReceiptPrinterComboBox;
import com.mbc.receiptprinter.ui.ReceiptPrinterLabel;
import com.mbc.receiptprinter.ui.constraints.ReceiptPrinterConstraints;
import com.mbc.receiptprinter.ui.totalyearlyamountreport.TotalYearlyAmountReportPrintButton;
import com.mbc.receiptprinter.ui.totalyearlyamountreport.TotalYearlyAmountReportGridBagLayout;
import com.mbc.receiptprinter.ui.totalyearlyamountreport.TotalYearlyAmountReportListener;
import com.mbc.receiptprinter.ui.totalyearlyamountreport.TotalYearlyAmountReportTable;
import com.mbc.receiptprinter.ui.totalyearlyamountreport.TotalYearlyAmountReportTableModel;
import com.mbc.receiptprinter.ui.totalyearlyamountreport.TotalYearlyAmountReportScrollPane;

public class TotalYearlyAmountReportTab extends JPanel {	
    private static final long serialVersionUID = 1L;

    private TotalYearlyAmountReportTable totalYearlyAmountReportTable;

    private JComboBox<String> receiptYears;

    private ReceiptFetchProcess receiptFetch;

    public TotalYearlyAmountReportTab() {
        receiptFetch = new ReceiptFetchProcess();

        setLayout(new TotalYearlyAmountReportGridBagLayout());

        add(new ReceiptPrinterLabel("Year:"), new ReceiptPrinterConstraints.Builder().gridx(2).gridy(1).anchor(GridBagConstraints.EAST).build());

        totalYearlyAmountReportTable = new TotalYearlyAmountReportTable(receiptFetch.getReceiptYears()[0]); // Gets the latest year on record

        receiptYears = new ReceiptPrinterComboBox("RECEIPT_YEARS", receiptFetch.getReceiptYears()); 
        receiptYears.addActionListener(new TotalYearlyAmountReportListener(this));
        receiptYears.setActionCommand(ActionCommand.YEARLY_REPORT_SELECTED_YEAR_CHANGED);
        add(receiptYears, new ReceiptPrinterConstraints.Builder().gridx(3).gridy(1).fill(GridBagConstraints.BOTH).anchor(GridBagConstraints.LINE_START).build());	

        add(new TotalYearlyAmountReportPrintButton(this), new ReceiptPrinterConstraints.Builder().gridx(3).gridy(6).anchor(GridBagConstraints.WEST).build());		
        add(new TotalYearlyAmountReportScrollPane(totalYearlyAmountReportTable), new ReceiptPrinterConstraints.Builder()
                                                                                                                   .gridx(1)
                                                                                                                   .gridy(7)
                                                                                                                   .fill(GridBagConstraints.BOTH)
                                                                                                                   .gridwidth(4)
                                                                                                                   .build());
	}

	public void refreshComboBoxes() {
		totalYearlyAmountReportTable.setModel(new TotalYearlyAmountReportTableModel(receiptFetch.getReceiptYears()[0])); // Gets the latest year on record
		receiptYears.setModel(new DefaultComboBoxModel<String>(receiptFetch.getReceiptYears()));
		repaint();
	}
	
	public TotalYearlyAmountReportTable getTotalYearlyAmountReportTable() { return totalYearlyAmountReportTable; }
	public JComboBox<String> getReceiptYears() { return receiptYears; }
	
}