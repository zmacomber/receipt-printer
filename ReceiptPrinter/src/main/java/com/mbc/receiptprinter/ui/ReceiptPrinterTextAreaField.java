package com.mbc.receiptprinter.ui;

import javax.swing.JTextArea;

import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

/**
 * Primary text area implementation for ReceiptPrinter
 */
public class ReceiptPrinterTextAreaField extends JTextArea {

	private static final long serialVersionUID = 1L;
	
	public ReceiptPrinterTextAreaField() {
		setFont(ReceiptPrinterUIUtils.getDefaultFont());
		setLineWrap(true);
		setWrapStyleWord(true);
		setAutoscrolls(true);
	}
}
