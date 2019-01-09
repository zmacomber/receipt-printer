package com.mbc.receiptprinter.converter;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.constant.FileDelimiters;

/**
 * Converts an Address into a String record for use in appending/deleting in an Address data file.
 * @see ConvertToStringRecord
 */
public class ConvertToStringRecordAddress implements ConvertToStringRecord<Address> {

	private Address address;
	
	public ConvertToStringRecordAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String build() {
		StringBuilder data = new StringBuilder();
		data.append(address.getId());
		data.append(FileDelimiters.FIELD);
		data.append(address.getName());
		data.append(FileDelimiters.FIELD);
		data.append(address.getAddress1());
		data.append(FileDelimiters.FIELD);
		data.append(address.getAddress2());
		data.append(FileDelimiters.FIELD);
		data.append(address.getCity());
		data.append(FileDelimiters.FIELD);
		data.append(address.getStateCode());
		data.append(FileDelimiters.FIELD);
		data.append(address.getZipCode());
		return data.toString();
	}
}
