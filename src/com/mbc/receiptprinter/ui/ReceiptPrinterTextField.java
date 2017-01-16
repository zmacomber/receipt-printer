package com.mbc.receiptprinter.ui;

import javax.swing.JTextField;

import com.mbc.receiptprinter.util.ReceiptPrinterProperties;
import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

/**
 * Primary text field implementation for ReceiptPrinter
 */
public class ReceiptPrinterTextField extends JTextField {

	private static final long serialVersionUID = 1L;
	
	public ReceiptPrinterTextField() {
		setFont(ReceiptPrinterUIUtils.getDefaultFont());
		setColumns(Integer.valueOf(ReceiptPrinterProperties.getProperty("receiptPrinter.default.textFieldSize")));
	}
}
