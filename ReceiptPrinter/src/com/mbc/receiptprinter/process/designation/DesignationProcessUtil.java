package com.mbc.receiptprinter.process.designation;

import java.util.List;

import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.util.ReceiptPrinterNameUtils;
import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

public class DesignationProcessUtil implements ReceiptPrinterNameUtils<Designation> {

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
