package com.mbc.receiptprinter.process.address;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.converter.ConvertFieldsToAddress;
import com.mbc.receiptprinter.dao.FetchDao;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;
import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

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