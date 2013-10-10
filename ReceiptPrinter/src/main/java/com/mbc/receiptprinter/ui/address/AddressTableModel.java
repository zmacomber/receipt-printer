package com.mbc.receiptprinter.ui.address;

import javax.swing.table.DefaultTableModel;

import com.mbc.receiptprinter.process.address.AddressFetchProcess;
import com.mbc.receiptprinter.ui.tabs.AddressTabColumns;

public class AddressTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	public AddressTableModel() {
		AddressFetchProcess fetchAddressProcess = new AddressFetchProcess();
		setDataVector(fetchAddressProcess.getAddressData(), AddressTabColumns.getAllNames());
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
