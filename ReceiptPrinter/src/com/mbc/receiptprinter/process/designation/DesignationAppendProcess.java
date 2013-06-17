package com.mbc.receiptprinter.process.designation;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.converter.ConvertFieldsToDesignation;
import com.mbc.receiptprinter.converter.ConvertToStringRecord;
import com.mbc.receiptprinter.converter.ConvertToStringRecordDesignation;
import com.mbc.receiptprinter.dao.AppendDao;
import com.mbc.receiptprinter.dao.FetchDao;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;
import com.mbc.receiptprinter.util.ReceiptPrinterNameUtils;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;
import com.mbc.receiptprinter.validator.DesignationValidator;

public class DesignationAppendProcess {
	public String appendDesignation(Designation designation) {
		if (DesignationValidator.designationIsInvalid(designation)) return ReceiptPrinterProperties.getProperty("designation.outcome.name_not_filled_in");
		
		AppendDao appendDao = new AppendDao();
		ConvertToStringRecord<Designation> convertToStringRecord = new ConvertToStringRecordDesignation(designation);
		FetchDao<Designation> designationFetchDao = new FetchDao<Designation>(); 
		ReceiptPrinterNameUtils<Designation> nameUtils = new DesignationProcessUtil();
		
		try {
			List<Designation> designations = designationFetchDao.fetchAll(FilePaths.DESIGNATION_DATA_PATH, new ConvertFieldsToDesignation());
			if (nameUtils.nameAlreadyExists(designation.getName(), designations)) {
				return ReceiptPrinterProperties.getProperty("designation.outcome.name_already_exists");
			}
			appendDao.append(FilePaths.DESIGNATION_DATA_PATH, convertToStringRecord);
			return ReceiptPrinterProperties.getProperty("designation.outcome.added");
		} catch (IOException ioe) {
			ReceiptPrinterLogger.logMessage(this.getClass(), Level.SEVERE, "IOException while appending designation - " + designation, ioe);
			return ReceiptPrinterProperties.getProperty("designation.outcome.added_error");
		}
	}
}
