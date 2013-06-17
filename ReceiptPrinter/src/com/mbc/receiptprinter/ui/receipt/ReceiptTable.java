package com.mbc.receiptprinter.ui.receipt;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.mbc.receiptprinter.ui.tabs.ReceiptTabColumns;

public class ReceiptTable extends JTable {
	
	private static final long serialVersionUID = 1L;

	public ReceiptTable() {
		setModel(new ReceiptTableModel());
		setToolTipText("To delete, press the \"Delete\" key.  To print, press the \"p\" key.");
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setPreferredScrollableViewportSize(new Dimension(500, 70));
		setFillsViewportHeight(true);
		getColumnModel().getColumn(ReceiptTabColumns.RECEIPT_DATE.getColumn()).setMaxWidth(80);
		getColumnModel().getColumn(ReceiptTabColumns.AMOUNT.getColumn()).setMaxWidth(60);
		getTableHeader().setBackground(Color.black);
		getTableHeader().setForeground(Color.white);
		addKeyListener(new ReceiptTableKeyListener(this));
	}
	
	public void repopulate() {
		setModel(new ReceiptTableModel());
		getColumnModel().getColumn(ReceiptTabColumns.RECEIPT_DATE.getColumn()).setMaxWidth(80);
		getColumnModel().getColumn(ReceiptTabColumns.AMOUNT.getColumn()).setMaxWidth(60);
		repaint();
	} 
}
