package com.mbc.receiptprinter.ui.receipt;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReceiptNotesScrollPane extends JScrollPane {

	private static final long serialVersionUID = 1L;

	public ReceiptNotesScrollPane(JTextArea notes) {
		setViewportView(notes);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setPreferredSize(new Dimension(300, 100));
	}
}
