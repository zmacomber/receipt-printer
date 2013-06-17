package com.mbc.receiptprinter.process.address;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.converter.ConvertFieldsToAddress;
import com.mbc.receiptprinter.dao.FetchDao;
import com.mbc.receiptprinter.ui.tabs.AddressTabColumns;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;
import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

public class AddressFetchProcess {
	public Address fetchAddress(long id) {
		if (id == 0) return null;
		Address fetched = null;
		for (Address address : fetchAddresses()) {
			if (address.getId() == id) {
				fetched = address;
				break;
			}
		}
		return fetched;
	}
	public Address fetchAddress(String name, String address1, String city, String stateCode) {
		if (ReceiptPrinterStringUtils.isNullOrEmpty(name) ||
			ReceiptPrinterStringUtils.isNullOrEmpty(address1) ||
			ReceiptPrinterStringUtils.isNullOrEmpty(city) ||
			ReceiptPrinterStringUtils.isNullOrEmpty(stateCode)) {
			
			return null;
			
		}
		Address fetched = null;
		for (Address address : fetchAddresses()) {
			if (address.getName().equals(name) &&
				address.getAddress1().equals(address1) &&
				address.getCity().equals(city) &&
				address.getStateCode().equals(stateCode)) {
				fetched = address;
				break;
			}
		}
		return fetched;
	}
	public Address fetchAddressFromReceipt(String receiptAddress) {
		if (ReceiptPrinterStringUtils.isNullOrEmpty(receiptAddress)) {
			return null;
		}
		String name = AddressProcessUtil.extractNameFromReceiptAddress(receiptAddress);
		String address1 = AddressProcessUtil.extractAddress1FromReceiptAddress(receiptAddress);
		String city = AddressProcessUtil.extractCityFromReceiptAddress(receiptAddress);
		String stateCode = AddressProcessUtil.extractStateCodeFromReceiptAddress(receiptAddress);
		
		return fetchAddress(name, address1, city, stateCode);
	}
	public List<Address> fetchAddresses() {
		FetchDao<Address> fetchAddressDao = new FetchDao<Address>();
		List<Address> addresses = new ArrayList<Address>();
		try {
			addresses = fetchAddressDao.fetchAll(FilePaths.ADDRESS_DATA_PATH, new ConvertFieldsToAddress());
		} catch (IOException e) {
			ReceiptPrinterLogger.logMessage(this.getClass(), Level.SEVERE, "IOException while fetching addresses", e);
		}
		return addresses;
	}
	public Object[][] getAddressData() {
		List<Address> addresses = fetchAddresses();
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
	public String[] getAddressesForReceipts() {
		List<Address> addresses = fetchAddresses();
		Collections.sort(addresses);
		if (addresses.size() == 0) return new String[] { "" };
		String[] addressesForReceipts = new String[addresses.size()];
		int counter = 0;
		for (Address addr : addresses) {
			addressesForReceipts[counter] = AddressProcessUtil.getAddressForReceipt(addr);
			counter++;
		}
		return addressesForReceipts;
	}
	public long getLastAddressId() {
		List<Address> addresses = fetchAddresses();
		if (addresses.size() == 0) {
			return 0;
		} else {
			return addresses.get(addresses.size() - 1).getId();
		}
	}
}