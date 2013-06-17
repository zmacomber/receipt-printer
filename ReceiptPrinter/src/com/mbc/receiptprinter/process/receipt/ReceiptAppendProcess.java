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

public class ReceiptAppendProcess {
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
