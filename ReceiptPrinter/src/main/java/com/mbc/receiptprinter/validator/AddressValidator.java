package com.mbc.receiptprinter.validator;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

public class AddressValidator {

	public static boolean addressIsInvalid(Address address) {
		if (address == null) return true;
		if (ReceiptPrinterStringUtils.isNullOrEmpty(address.getName()) ||
			ReceiptPrinterStringUtils.isNullOrEmpty(address.getAddress1()) || (address.getAddress2() == null) ||
			ReceiptPrinterStringUtils.isNullOrEmpty(address.getCity()) ||
			ReceiptPrinterStringUtils.isNullOrEmpty(address.getZipCode()) ||
			ReceiptPrinterStringUtils.isNullOrEmpty(address.getStateCode())) {
			return true;
		}
		return false;
	}
}
