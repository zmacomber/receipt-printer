package com.mbc.receiptprinter.process.address;

import java.util.List;

import com.mbc.receiptprinter.bean.Address;

public class AddressProcessUtil {

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
	
	public static boolean addressContainsInvalidCharacters(Address addressToCheck) {
		if (addressToCheck.getName().contains("(") || addressToCheck.getName().contains(")") || addressToCheck.getName().contains(",") ||
			addressToCheck.getCity().contains("(") || addressToCheck.getCity().contains(")") || addressToCheck.getCity().contains(",") ||
			addressToCheck.getAddress1().contains("(") || addressToCheck.getAddress1().contains(")") || addressToCheck.getAddress1().contains(",")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String getAddressForReceipt(Address addr) {
		return addr.getName() + " (" + addr.getAddress1() + ", " + addr.getCity() + ", " + addr.getStateCode() + ")";
	}
	
	public static String extractNameFromReceiptAddress(String receiptAddress) {
		int endIndex = receiptAddress.indexOf(" (");
		return receiptAddress.substring(0, endIndex);
	}
	
	public static String extractAddress1FromReceiptAddress(String receiptAddress) {
		int startIndex = receiptAddress.indexOf("(") + 1;
		int endIndex = receiptAddress.indexOf(", ");
		return receiptAddress.substring(startIndex, endIndex);
	}
	
	public static String extractCityFromReceiptAddress(String receiptAddress) {
		return receiptAddress.split(", ")[1];
	}
	
	public static String extractStateCodeFromReceiptAddress(String receiptAddress) {
		String[] s = receiptAddress.split(", ");
		return s[2].substring(0, s[2].length() - 1);
	}
}
