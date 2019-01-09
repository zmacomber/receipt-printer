package com.mbc.receiptprinter.ui.tabs;

import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.constant.ActionCommand;
import com.mbc.receiptprinter.process.receipt.ReceiptFetchProcess;
import com.mbc.receiptprinter.ui.ReceiptPrinterComboBox;
import com.mbc.receiptprinter.ui.ReceiptPrinterLabel;
import com.mbc.receiptprinter.ui.constraints.ReceiptPrinterConstraints;
import com.mbc.receiptprinter.ui.totalyearlyamountreport.*;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TotalYearlyAmountReportTab extends JPanel {	
    private static final long serialVersionUID = 1L;

    private TotalYearlyAmountReportTable totalYearlyAmountReportTable;

    private JComboBox<String> receiptYears;

    private ReceiptFetchProcess receiptFetch;

    public TotalYearlyAmountReportTab() {
        receiptFetch = new ReceiptFetchProcess();

        setLayout(new TotalYearlyAmountReportGridBagLayout());

        add(new ReceiptPrinterLabel("Year:"), new ReceiptPrinterConstraints.Builder().gridx(2).gridy(1).anchor(GridBagConstraints.EAST).build());

        totalYearlyAmountReportTable = new TotalYearlyAmountReportTable(getReceiptYearsArr()[0]); // Gets the latest year on record

        receiptYears = new ReceiptPrinterComboBox("RECEIPT_YEARS", getReceiptYearsArr());
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
		totalYearlyAmountReportTable.setModel(new TotalYearlyAmountReportTableModel(getReceiptYearsArr()[0])); // Gets the latest year on record
		receiptYears.setModel(new DefaultComboBoxModel<String>(getReceiptYearsArr()));
		repaint();
	}

    /**
     * Used for populating the year address combo box on the total yearly amount report
     * @return A String array of receipt years in descending order
     */
    public String[] getReceiptYearsArr() {
        Set<String> receiptYears = new TreeSet<String>(
                new Comparator<String>() {
                    // Sort descending with newest year at the top
                    public int compare(String s1, String s2) {
                        return Integer.valueOf(s2) - Integer.valueOf(s1);
                    }
                }
        );
        List<Receipt> receipts = receiptFetch.fetchReceipts();
        for (Receipt receipt : receipts) {
            receiptYears.add(receipt.getReceiptDate().substring(6)); // Dates are in MM/DD/YYYY format so the year starts at index 6
        }
        if (receiptYears.size() == 0) {
            return new String[] { "" };
        } else {
            return receiptYears.toArray(new String[0]);
        }
    }
	
	public TotalYearlyAmountReportTable getTotalYearlyAmountReportTable() { return totalYearlyAmountReportTable; }
	public JComboBox<String> getReceiptYears() { return receiptYears; }
	
}