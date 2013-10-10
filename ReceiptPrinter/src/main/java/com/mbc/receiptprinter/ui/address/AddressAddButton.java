package com.mbc.receiptprinter.ui.address;

import javax.swing.JButton;

import com.mbc.receiptprinter.constant.ActionCommand;
import com.mbc.receiptprinter.ui.tabs.AddressTab;
import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

public class AddressAddButton extends JButton {

	private static final long serialVersionUID = 1L;

	public AddressAddButton(AddressTab addressTab) {
		setText("Add Address");
		setActionCommand(ActionCommand.ADD_ADDRESS);
		addActionListener(new AddressActionListener(addressTab));
		setFont(ReceiptPrinterUIUtils.getDefaultFont());
	}
}
