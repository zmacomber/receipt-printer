package com.mbc.receiptprinter.ui.designation;

import javax.swing.JScrollPane;

public class DesignationTableScrollPane extends JScrollPane {

	private static final long serialVersionUID = 1L;

	public DesignationTableScrollPane(DesignationTable designationTable) {
		setViewportView(designationTable);
		setToolTipText("");
	}
}
