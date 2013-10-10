package com.mbc.receiptprinter.ui.receipt;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.mbc.receiptprinter.util.ReceiptPrinterProperties;

public class ReceiptNotesScrollPane extends JScrollPane {

	private static final long serialVersionUID = 1L;

	public ReceiptNotesScrollPane(JTextArea notes) {
		setViewportView(notes);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		int width = Integer.valueOf(ReceiptPrinterProperties.getProperty("receiptNotesScrollPane.width"));
		int height = Integer.valueOf(ReceiptPrinterProperties.getProperty("receiptNotesScrollPane.height"));
		setPreferredSize(new Dimension(width, height));
	}
}
