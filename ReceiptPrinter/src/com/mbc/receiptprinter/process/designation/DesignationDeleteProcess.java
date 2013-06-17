package com.mbc.receiptprinter.process.designation;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.converter.ConvertFieldsToDesignation;
import com.mbc.receiptprinter.converter.ConvertToStringRecordDesignation;
import com.mbc.receiptprinter.dao.DeleteDao;
import com.mbc.receiptprinter.dao.FetchDao;
import com.mbc.receiptprinter.process.receipt.ReceiptFetchProcess;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;
import com.mbc.receiptprinter.validator.DesignationValidator;

public class DesignationDeleteProcess {

	public String deleteDesignation(Designation designation) {
		if (DesignationValidator.designationIsInvalid(designation)) return ReceiptPrinterProperties.getProperty("designation.outcome.deleted_is_invalid");
		if (designationExistsInReceipt(designation)) return ReceiptPrinterProperties.getProperty("designation.outcome.designation_exists_in_receipt");
		DeleteDao deleteDao = new DeleteDao();
		FetchDao<Designation> designationFetchDao = new FetchDao<Designation>();
		String recordToDelete = new ConvertToStringRecordDesignation(designation).build();
		try {
			List<Designation> designations = designationFetchDao.fetchAll(FilePaths.DESIGNATION_DATA_PATH, new ConvertFieldsToDesignation());
			boolean designationNotFound = true;
			for (Designation d : designations) {
				if (d.getName().equals(designation.getName())) {
					designationNotFound = false;
					break;
				}
			}
			if (designationNotFound) { return ReceiptPrinterProperties.getProperty("designation.outcome.not_found"); }
			deleteDao.delete(FilePaths.DESIGNATION_DATA_PATH, recordToDelete);
			return ReceiptPrinterProperties.getProperty("designation.outcome.deleted");
		} catch (IOException ioe) {
			ReceiptPrinterLogger.logMessage(this.getClass(), Level.SEVERE, "IOException while deleting designation " + designation, ioe);
			return ReceiptPrinterProperties.getProperty("designation.outcome.deleted_error");
		}
	}

	boolean designationExistsInReceipt(Designation designation) {
		ReceiptFetchProcess receiptFetch = new ReceiptFetchProcess();
		List<Receipt> receipts = receiptFetch.fetchReceipts();
		for (Receipt receipt : receipts) {
			if (receipt.getDesignation().equals(designation)) {
				return true;
			}
		}				
		return false;
	}
}
