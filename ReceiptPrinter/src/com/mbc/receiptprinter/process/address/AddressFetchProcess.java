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

/**
 * Responsible for the fetching of Addresses
 */
public class AddressFetchProcess {
	
	/**
	 * Fetches an Address by the Address id
	 * @param id The id is the first field in an Address record
	 * @return The Address that contains the given id; otherwise null
	 */
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
	
	/**
	 * Fetches an Address by it's key fields.  The combination of name, address1, city and stateCode must
	 * be unique in the application
	 * @param name The Address name
	 * @param address1 The primary street, road, p.o. box, etc... for this Address
	 * @param city The city of the Address
	 * @param stateCode The stateCode (i.e. NY as opposed to New York) of the Address
	 * @return The Address that contains the given params; otherwise null
	 */
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
	
	/**
	 * Fetches an Address from a receipt address.  A receipt address is formatted
	 * like this: Address name (address1, city, stateCode)
	 * @param receiptAddress An address that's formatted specifically for use in receipts
	 * @return The Address that matches the name, address1, city and stateCode extracted from 
	 * the receipt address; otherwise null
	 */
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
	
	/**
	 * Fetches all Address records
	 * @return A List of all of the Address records in the Address data file; otherwise an empty list 
	 */
	public List<Address> fetchAddresses() {
		FetchDao<Address> fetchAddressDao = new FetchDao<Address>();
		List<Address> addresses = new ArrayList<Address>();
		try {
			addresses = fetchAddressDao.fetchAll(FilePaths.ADDRESS_DATA.getPath(), new ConvertFieldsToAddress());
		} catch (IOException e) {
			ReceiptPrinterLogger.logMessage(this.getClass(), Level.SEVERE, "IOException while fetching addresses", e);
		}
		return addresses;
	}
	
	/**
	 * Formats the Address records in such a way as to be able to display them in a table on the user interface
	 * @return A two dimensional Object array of Address data that is used for display purposes
	 */
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
	
	/**
	 * Used for populating receipt address combo boxes on the user interface
	 * @return A String array of addresses formatted for display on the receipt page
	 */
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
	
	/**
	 * Gets the last Address id in the Address data file
	 * @return The last Address id found; otherwise zero
	 */
	public long getLastAddressId() {
		long lastAddressId = 0;
		for (Address addr : fetchAddresses()) {
			if (addr.getId() > lastAddressId) {
				lastAddressId = addr.getId();
			}
		}
		return lastAddressId;
	}
}