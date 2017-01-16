package com.mbc.receiptprinter.converter;

import com.mbc.receiptprinter.bean.Designation;

/**
 * Converts a Designation into a String record for use in appending/deleting in a Designation data file.
 * @see ConvertToStringRecord
 */
public class ConvertToStringRecordDesignation implements ConvertToStringRecord<Designation> {

	private Designation designation;
	
	public ConvertToStringRecordDesignation(Designation designation) {
		this.designation = designation;
	}
	
	@Override
	public String build() {
		return designation.getName();
	}
}
