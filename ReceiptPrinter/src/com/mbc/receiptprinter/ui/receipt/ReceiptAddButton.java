package com.mbc.receiptprinter.ui.receipt;

import java.awt.Font;

import javax.swing.JButton;

import com.mbc.receiptprinter.constant.ActionCommand;
import com.mbc.receiptprinter.ui.tabs.ReceiptTab;

public class ReceiptAddButton extends JButton {

	private static final long serialVersionUID = 1L;

	public ReceiptAddButton(ReceiptTab receiptTab) {
		setText("Add Receipt");
		setActionCommand(ActionCommand.ADD_RECEIPT);
		addActionListener(new ReceiptActionListener(receiptTab));
		setFont(new Font("Tahoma", Font.PLAIN, 12));
	}
}
