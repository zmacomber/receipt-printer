package com.mbc.receiptprinter.ui.designation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class DesignationTable extends JTable {
	
	private static final long serialVersionUID = 1L;

	public DesignationTable() {
		setModel(new DesignationTableModel());
		setToolTipText("To delete, press the \"Delete\" key");
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
