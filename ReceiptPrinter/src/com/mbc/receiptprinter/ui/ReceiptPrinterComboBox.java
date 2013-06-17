package com.mbc.receiptprinter.ui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

public class ReceiptPrinterComboBox extends JComboBox<String> {

	private static final long serialVersionUID = 1L;
	
	public ReceiptPrinterComboBox(String name, String[] items) {
		setName(name);
		setModel(new DefaultComboBoxModel<String>(items));
		setFont(ReceiptPrinterUIUtils.getDefaultFont());
	}
}
