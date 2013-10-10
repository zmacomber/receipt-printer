package com.mbc.receiptprinter.ui.receipt;

import javax.swing.JScrollPane;

public class ReceiptTableScrollPane extends JScrollPane {

	private static final long serialVersionUID = 1L;

	public ReceiptTableScrollPane(ReceiptTable receiptTable) {
		setViewportView(receiptTable);
		setToolTipText("");
	}
}
