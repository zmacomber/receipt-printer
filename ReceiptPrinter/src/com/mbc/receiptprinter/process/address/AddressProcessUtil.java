package com.mbc.receiptprinter.process.address;

import java.util.List;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

/**
 * Various helper methods used in the processing of Address records
 */
public class AddressProcessUtil {

	/**
	 * Checks to see if an Address already exists
	 * @param addressToCheck The Address to verify
	 * @param addresses The List of Address records to scan for the addressToCheck
	 * @return true if the addressToCheck is found in the addresses List; false otherwise
	 */
	public static boolean addressAlreadyExists(Address addressToCheck, List<Address> addresses) {
		if ((addressToCheck == null) || (addresses == null)) return false;
		boolean addressAlreadyExists = false;
		for (Address address : addresses) {
			if (address.equals(addressToCheck)) {
				addressAlreadyExists = true;
				break;
			}
		}
		return addressAlreadyExists;
	}
	
	/**
	 * Checks to see if invalid characters [ "(", ")", or "," ] are in the addressToCheck.
	 * These characters are invalid as they are used in the display of addresses formatted for receipts
	 * @param addressToCheck The Address to verify
	 * @return true if the Address name, city or address1 contain the invalid characters; false otherwise
	 */
	public static boolean addressContainsInvalidCharacters(Address addressToCheck) {
		if (addressToCheck.getName().contains("(") || addressToCheck.getName().contains(")") || addressToCheck.getName().contains(",") ||
			addressToCheck.getCity().contains("(") || addressToCheck.getCity().contains(")") || addressToCheck.getCity().contains(",") ||
			addressToCheck.getAddress1().contains("(") || addressToCheck.getAddress1().contains(")") || addressToCheck.getAddress1().contains(",")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Formats an Address record for display in the Receipt page
	 * @param addr The Address to format
	 * @return An Address record formatted for display in the Receipt page
	 */
	public static String getAddressForReceipt(Address addr) {
		return addr.getName() + " (" + addr.getAddress1() + ", " + addr.getCity() + ", " + addr.getStateCode() + ")";
	}
	
	/**
	 * Extracts the Address name field from the supplied receipt address
	 * @param receiptAddress An Address formatted for display in the Receipt page
	 * @return The Address name field; otherwise null
	 */
	public static String extractNameFromReceiptAddress(String receiptAddress) {
		if (ReceiptPrinterStringUtils.isNullOrEmpty(receiptAddress)) return null;
		
		int endIndex = receiptAddress.indexOf(" (");
		if (endIndex > 0) {
			return receiptAddress.substring(0, endIndex);
		} else {
			return null;
		}
	}
	
	/**
	 * Extracts the Address address1 field from the supplied receipt address
	 * @param receiptAddress An Address formatted for display in the Receipt page
	 * @return The Address address1 field; otherwise null
	 */
	public static String extractAddress1FromReceiptAddress(String receiptAddress) {
		if (ReceiptPrinterStringUtils.isNullOrEmpty(receiptAddress)) return null;
		
		int startIndex = receiptAddress.indexOf("(") + 1;
		int endIndex = receiptAddress.indexOf(", ");
		if ((startIndex > 0) && (endIndex > 0)) {
			return receiptAddress.substring(startIndex, endIndex);
		} else {
			return null;
		}
	}
	
	/**
	 * Extracts the Address city field from the supplied receipt address
	 * @param receiptAddress An Address formatted for display in the Receipt page
	 * @return The Address city field; otherwise null
	 */
	public static String extractCityFromReceiptAddress(String receiptAddress) {
		if (ReceiptPrinterStringUtils.isNullOrEmpty(receiptAddress)) return null;
		
		String[] s = receiptAddress.split(", ");
		if (s.length >= 2) {
			return s[1];
		} else {
			return null;
		}
	}
	
	/**
	 * Extracts the Address stateCode field from the supplied receipt address
	 * @param receiptAddress An Address formatted for display in the Receipt page
	 * @return The Address stateCode field; otherwise null
	 */
	public static String extractStateCodeFromReceiptAddress(String receiptAddress) {
		if (ReceiptPrinterStringUtils.isNullOrEmpty(receiptAddress)) return null;
		
		String[] s = receiptAddress.split(", ");
		if (s.length >= 3) {
			return s[2].substring(0, s[2].length() - 1);
		} else {
			return null;
		}
	}
}
