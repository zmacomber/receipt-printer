package com.mbc.receiptprinter.ui.address;

import javax.swing.JScrollPane;

public class AddressTableScrollPane extends JScrollPane {

	private static final long serialVersionUID = 1L;

	public AddressTableScrollPane(AddressTable addressTable) {
		setViewportView(addressTable);
		setToolTipText("");
	}
}
