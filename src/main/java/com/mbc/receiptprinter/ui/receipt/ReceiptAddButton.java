package com.mbc.receiptprinter.ui.receipt;

import javax.swing.JButton;

import com.mbc.receiptprinter.constant.ActionCommand;
import com.mbc.receiptprinter.ui.tabs.ReceiptTab;
import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

public class ReceiptAddButton extends JButton {

	private static final long serialVersionUID = 1L;

	public ReceiptAddButton(ReceiptTab receiptTab) {
		setText("Add Receipt");
		setActionCommand(ActionCommand.ADD_RECEIPT);
		addActionListener(new ReceiptActionListener(receiptTab));
		setFont(ReceiptPrinterUIUtils.getDefaultFont());
	}
}
