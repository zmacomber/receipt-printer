package com.mbc.receiptprinter.ui.designation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.mbc.receiptprinter.util.ReceiptPrinterProperties;

public class DesignationTable extends JTable {
	
	private static final long serialVersionUID = 1L;

	public DesignationTable() {
		setModel(new DesignationTableModel());
		setToolTipText(ReceiptPrinterProperties.getProperty("table.deleteTooltip"));
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setPreferredScrollableViewportSize(new Dimension(250, 70));
		setFillsViewportHeight(true);
		getTableHeader().setBackground(Color.black);
		getTableHeader().setForeground(Color.white);
		addKeyListener(new DesignationTableKeyListener(this));
	}
	
	public void repopulate() {
		setModel(new DesignationTableModel());
		repaint();
	} 
}
