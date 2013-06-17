package com.mbc.receiptprinter.ui.designation;

import javax.swing.table.DefaultTableModel;

import com.mbc.receiptprinter.process.designation.DesignationFetchProcess;

public class DesignationTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	public DesignationTableModel() {
		DesignationFetchProcess designationFetchProcess = new DesignationFetchProcess();
		setDataVector(designationFetchProcess.getAddressData(), new Object[] { "Name" } );
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
