package com.mbc.receiptprinter.validator;

import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

public class DesignationValidator {

	public static boolean designationIsInvalid(Designation designation) {
		if (designation == null) return true;
		if (ReceiptPrinterStringUtils.isNullOrEmpty(designation.getName())) {
			return true;
		}
		return false;
	}
}
