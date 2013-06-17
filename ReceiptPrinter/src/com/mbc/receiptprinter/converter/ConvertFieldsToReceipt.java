package com.mbc.receiptprinter.converter;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.process.address.AddressFetchProcess;
import com.mbc.receiptprinter.process.designation.DesignationFetchProcess;
import com.mbc.receiptprinter.ui.tabs.ReceiptTabColumns;

/**
* Converts fields found in a line from a Receipt data file into a Receipt bean
* @see ConvertFields
*/
public class ConvertFieldsToReceipt implements ConvertFields<Receipt> {

	/**
	* @throws IllegalArgumentException If fields is null or not the correct length
	*/
	public Receipt convert(String[] fields) {
		if ((fields == null) || (fields.length != ReceiptTabColumns.values().length)) {
			throw new IllegalArgumentException("Fields param (" + fields + ") must not be null and have a length of " + ReceiptTabColumns.values().length); 
		}
		AddressFetchProcess afp = new AddressFetchProcess();
		Address addr = afp.fetchAddress(Long.valueOf(fields[ReceiptTabColumns.ADDRESS.getColumn()]));
		
		DesignationFetchProcess dfp = new DesignationFetchProcess();
		Designation designation = dfp.fetchDesignation(fields[ReceiptTabColumns.DESIGNATION.getColumn()]);
		
		return Receipt.newInstance(fields[ReceiptTabColumns.RECEIPT_DATE.getColumn()], 
								   addr,
								   designation,
								   fields[ReceiptTabColumns.AMOUNT.getColumn()],
								   fields[ReceiptTabColumns.NOTES.getColumn()]);
	}
	
	public boolean hasMultipleFields() { return true; }
}
