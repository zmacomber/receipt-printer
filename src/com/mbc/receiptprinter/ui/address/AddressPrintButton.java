package com.mbc.receiptprinter.ui.address;

import javax.swing.JButton;

import com.mbc.receiptprinter.constant.ActionCommand;
import com.mbc.receiptprinter.ui.tabs.AddressTab;
import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

public class AddressPrintButton extends JButton {

	private static final long serialVersionUID = 1L;

	public AddressPrintButton(AddressTab addressTab) {
		setText("Print Addresses");
		setActionCommand(ActionCommand.PRINT_ADDRESSES);
		addActionListener(new AddressActionListener(addressTab));
		setFont(ReceiptPrinterUIUtils.getDefaultFont());
	}
}
