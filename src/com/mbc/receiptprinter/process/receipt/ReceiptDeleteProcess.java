package com.mbc.receiptprinter.process.receipt;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.converter.ConvertFieldsToReceipt;
import com.mbc.receiptprinter.converter.ConvertToStringRecordReceipt;
import com.mbc.receiptprinter.dao.DeleteDao;
import com.mbc.receiptprinter.dao.FetchDao;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;
import com.mbc.receiptprinter.validator.ReceiptValidator;

/**
 * Deletes a Receipt from the Receipt data file
 */
public class ReceiptDeleteProcess {

	/**
	 * Deletes a Receipt from the Receipt data file.  If validation errors occur during processing, a message will
	 * be sent back to the caller indicating the errors.
	 * @param receipt The Receipt to delete
	 * @return An outcome message indicating if the delete was successful or not
	 */
	public String deleteReceipt(Receipt receipt) {
		if (ReceiptValidator.receiptIsInvalid(receipt)) return ReceiptPrinterProperties.getProperty("receipt.outcome.deleted_is_invalid");
		DeleteDao deleteDao = new DeleteDao();
		FetchDao<Receipt> fetchDao = new FetchDao<Receipt>();
		String recordToDelete = new ConvertToStringRecordReceipt(receipt).build();
		try {
			List<Receipt> receipts = fetchDao.fetchAll(FilePaths.RECEIPT_DATA.getPath(), new ConvertFieldsToReceipt());
			boolean receiptNotFound = true;
			for (Receipt r : receipts) {
				if (r.getReceiptDate().equals(receipt.getReceiptDate()) &&
					r.getAddress().equals(receipt.getAddress()) &&
					r.getDesignation().equals(receipt.getDesignation()) &&
					r.getAmount().equals(receipt.getAmount())) {
					receiptNotFound = false;
					break;
				}
			}
			if (receiptNotFound) { return ReceiptPrinterProperties.getProperty("receipt.outcome.not_found"); }
			deleteDao.delete(FilePaths.RECEIPT_DATA.getPath(), recordToDelete);
			return ReceiptPrinterProperties.getProperty("receipt.outcome.deleted");
		} catch (IOException ioe) {
			ReceiptPrinterLogger.logMessage(this.getClass(), Level.SEVERE, "IOException while deleting receipt " + receipt, ioe);
			return ReceiptPrinterProperties.getProperty("receipt.outcome.deleted_error");
		}
	}
}
