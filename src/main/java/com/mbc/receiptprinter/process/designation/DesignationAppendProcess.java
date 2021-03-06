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

/**
 * Appends a Designation to the Designation data file
 */
public class DesignationAppendProcess {
	
	/**
	 * Appends a Designation to the Designation data file.  If validation errors occur, a message will be sent back to the caller
	 * indicating what errors occurred.
	 * @param designation The Designation to append to the Designation data file
	 * @return An outcome message that indicates if the append was successful or otherwise
	 */
	public String appendDesignation(Designation designation) {
		if (DesignationValidator.designationIsInvalid(designation)) return ReceiptPrinterProperties.getProperty("designation.outcome.name_not_filled_in");
		
		AppendDao appendDao = new AppendDao();
		ConvertToStringRecord<Designation> convertToStringRecord = new ConvertToStringRecordDesignation(designation);
		FetchDao<Designation> designationFetchDao = new FetchDao<Designation>(); 
		ReceiptPrinterNameUtils<Designation> nameUtils = new DesignationProcessUtil();
		
		try {
			List<Designation> designations = designationFetchDao.fetchAll(FilePaths.DESIGNATION_DATA.getPath(), new ConvertFieldsToDesignation());
			if (nameUtils.nameAlreadyExists(designation.getName(), designations)) {
				return ReceiptPrinterProperties.getProperty("designation.outcome.name_already_exists");
			}
			appendDao.append(FilePaths.DESIGNATION_DATA.getPath(), convertToStringRecord);
			return ReceiptPrinterProperties.getProperty("designation.outcome.added");
		} catch (IOException ioe) {
			ReceiptPrinterLogger.logMessage(this.getClass(), Level.SEVERE, "IOException while appending designation - " + designation, ioe);
			return ReceiptPrinterProperties.getProperty("designation.outcome.added_error");
		}
	}
}
