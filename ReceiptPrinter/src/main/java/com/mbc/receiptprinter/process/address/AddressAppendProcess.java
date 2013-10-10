package com.mbc.receiptprinter.process.address;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.converter.ConvertFieldsToAddress;
import com.mbc.receiptprinter.converter.ConvertToStringRecord;
import com.mbc.receiptprinter.converter.ConvertToStringRecordAddress;
import com.mbc.receiptprinter.dao.AppendDao;
import com.mbc.receiptprinter.dao.FetchDao;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;
import com.mbc.receiptprinter.validator.AddressValidator;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Appends an Address to the Address data file
 */
public class AddressAppendProcess {

	/**
	 * Appends an Address to the Address data file.  If validation errors occur, a message will be sent back to the caller
	 * indicating what errors occurred.
	 * @param address The Address to append to the Address data file
	 * @return An outcome message that indicates if the append was successful or otherwise
	 */
	public String appendAddress(Address address) {
		if (AddressValidator.addressIsInvalid(address)) return ReceiptPrinterProperties.getProperty("address.outcome.added_not_filled_in");
		
		AppendDao appendDao = new AppendDao();
		ConvertToStringRecord<Address> convertToStringRecord = new ConvertToStringRecordAddress(address);
		FetchDao<Address> fetchAddressDao = new FetchDao<Address>();
		
		try {
			if (AddressProcessUtil.addressAlreadyExists(address, fetchAddressDao.fetchAll(FilePaths.ADDRESS_DATA.getPath(), new ConvertFieldsToAddress()))) {
				return ReceiptPrinterProperties.getProperty("address.outcome.already_exists");
			}
			if (AddressProcessUtil.addressContainsInvalidCharacters(address)) {
				return ReceiptPrinterProperties.getProperty("address.outcome.invalid_characters");
			}
			appendDao.append(FilePaths.ADDRESS_DATA.getPath(), convertToStringRecord);
			return ReceiptPrinterProperties.getProperty("address.outcome.added");
		} catch (IOException ioe) {
			ReceiptPrinterLogger.logMessage(this.getClass(), Level.SEVERE, "IOException while appending address - " + address, ioe);
			return ReceiptPrinterProperties.getProperty("address.outcome.error");
		}
	}
}
