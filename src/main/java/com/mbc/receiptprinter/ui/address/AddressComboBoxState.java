package com.mbc.receiptprinter.ui.address;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.mbc.receiptprinter.constant.StateCode;
import com.mbc.receiptprinter.ui.tabs.AddressTabColumns;
import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

public class AddressComboBoxState extends JComboBox<String> {

	private static final long serialVersionUID = 1L;
	
	public AddressComboBoxState() {
		setName(AddressTabColumns.STATE.getName());
		setModel(new DefaultComboBoxModel<String>(StateCode.getNames()));
		setFont(ReceiptPrinterUIUtils.getDefaultFont());
	}
}
