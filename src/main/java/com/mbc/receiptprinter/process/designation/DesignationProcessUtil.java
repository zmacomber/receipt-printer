package com.mbc.receiptprinter.process.designation;

import java.util.List;

import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.util.ReceiptPrinterNameUtils;
import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

/**
 * Various helper methods used in the processing of Designation records
 */
public class DesignationProcessUtil implements ReceiptPrinterNameUtils<Designation> {

	/**
	 * Checks to see if a Designation name already exists
	 * @param name The Designation to verify
	 * @param designations The List of Designation records to scan for the name
	 * @return true if the name is found in the designations List; false otherwise
	 */
	@Override
	public boolean nameAlreadyExists(String name, List<Designation> designations) {
		if (ReceiptPrinterStringUtils.isNullOrEmpty(name) || (designations == null)) return false;
		boolean nameAlreadyExists = false;
		for (Designation designation : designations) {
			if (designation.getName().trim().equalsIgnoreCase(name.trim())) {
				nameAlreadyExists = true;
				break;
			}
		}
		return nameAlreadyExists;
	}
}
