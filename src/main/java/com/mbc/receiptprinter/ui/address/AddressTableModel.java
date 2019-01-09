package com.mbc.receiptprinter.ui.address;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.process.address.AddressFetchProcess;
import com.mbc.receiptprinter.ui.tabs.AddressTabColumns;

import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.List;

public class AddressTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	private final AddressFetchProcess fetchAddressProcess = new AddressFetchProcess();
	
	public AddressTableModel() {
		setDataVector(getAddressData(), AddressTabColumns.getAllNames());
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	/**
	 * Formats the Address records in such a way as to be able to display them in a table on the user interface
	 * @return A two dimensional Object array of Address data that is used for display purposes
	 */
	public Object[][] getAddressData() {
		List<Address> addresses = fetchAddressProcess.fetchAddresses();
		Collections.sort(addresses);
		Object[][] data = new Object[addresses.size()][AddressTabColumns.values().length];
		int counter = 0;
		for (Address addr : addresses) {
			data[counter][AddressTabColumns.NAME.getColumn()] = addr.getName();
			data[counter][AddressTabColumns.ADDRESS1.getColumn()] = addr.getAddress1();
			data[counter][AddressTabColumns.ADDRESS2.getColumn()] = addr.getAddress2();
			data[counter][AddressTabColumns.CITY.getColumn()] = addr.getCity();
			data[counter][AddressTabColumns.STATE.getColumn()] = addr.getStateCode();
			data[counter][AddressTabColumns.ZIP.getColumn()] = addr.getZipCode();
			counter++;
		}
		return data;
	}

}
