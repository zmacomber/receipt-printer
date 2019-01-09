package com.mbc.receiptprinter.ui.designation;

import javax.swing.JButton;

import com.mbc.receiptprinter.constant.ActionCommand;
import com.mbc.receiptprinter.ui.tabs.DesignationTab;
import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

public class DesignationAddButton extends JButton {

	private static final long serialVersionUID = 1L;

	public DesignationAddButton(DesignationTab designationTab) {
		setText("Add Designation");
		setActionCommand(ActionCommand.ADD_DESIGNATION);
		addActionListener(new DesignationActionListener(designationTab));
		setFont(ReceiptPrinterUIUtils.getDefaultFont());
	}
}
