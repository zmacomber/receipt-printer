package com.mbc.receiptprinter.ui;

import javax.swing.JLabel;

import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

public class ReceiptPrinterLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	public ReceiptPrinterLabel(String text) {
		setText(text);
		setFont(ReceiptPrinterUIUtils.getDefaultFont());
	}
	
}
