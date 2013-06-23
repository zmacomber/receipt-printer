package com.mbc.receiptprinter.process.receipt;

import java.io.IOException;
import java.util.logging.Level;

import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.converter.ConvertFieldsToReceipt;
import com.mbc.receiptprinter.converter.ConvertToStringRecord;
import com.mbc.receiptprinter.converter.ConvertToStringRecordReceipt;
import com.mbc.receiptprinter.dao.AppendDao;
import com.mbc.receiptprinter.dao.FetchDao;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;
import com.mbc.receiptprinter.validator.ReceiptValidator;

/**
 * Appends a Receipt to the Receipt data file
 */
public class ReceiptAppendProcess {
	
	/**
	 * Appends a Receipt to the Receipt data file.  If validation errors occur, a message will be sent back to the caller
	 * indicating what errors occurred.
	 * @param receipt The Receipt to append to the Receipt data file
	 * @return An outcome message that indicates if the append was successful or otherwise
	 */
	public String appendReceipt(Receipt receipt) {
		if (ReceiptValidator.receiptIsInvalid(receipt)) return ReceiptPrinterProperties.getProperty("receipt.outcome.added_is_invalid");
		
		AppendDao appendDao = new AppendDao();
		ConvertToStringRecord<Receipt> convertToStringRecord = new ConvertToStringRecordReceipt(receipt);
		FetchDao<Receipt> fetchReceiptDao = new FetchDao<Receipt>();
		
		try {
			if (ReceiptProcessUtil.receiptAlreadyExists(receipt, fetchReceiptDao.fetchAll(FilePaths.RECEIPT_DATA_PATH, new ConvertFieldsToReceipt()))) {
				return ReceiptPrinterProperties.getProperty("receipt.outcome.already_exists");
			}
			appendDao.append(FilePaths.RECEIPT_DATA_PATH, convertToStringRecord);
			return ReceiptPrinterProperties.getProperty("receipt.outcome.added");
		} catch (IOException ioe) {
			ReceiptPrinterLogger.logMessage(this.getClass(), Level.SEVERE, "IOException while appending receipt - " + receipt, ioe);
			return ReceiptPrinterProperties.getProperty("receipt.outcome.error");
		}
	}
}
