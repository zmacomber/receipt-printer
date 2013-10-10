package com.mbc.receiptprinter.process.address;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.converter.ConvertFieldsToAddress;
import com.mbc.receiptprinter.converter.ConvertToStringRecordAddress;
import com.mbc.receiptprinter.dao.DeleteDao;
import com.mbc.receiptprinter.dao.FetchDao;
import com.mbc.receiptprinter.process.receipt.ReceiptFetchProcess;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;
import com.mbc.receiptprinter.validator.AddressValidator;

/**
 * Deletes an Address from the Address data file
 */
public class AddressDeleteProcess {

	/**
	 * Deletes an Address from the Address data file.  If validation errors occur during processing, a message will
	 * be sent back to the caller indicating the errors.
	 * @param addr The Address to delete
	 * @return An outcome message indicating if the delete was successful or not
	 */
	public String deleteAddress(Address addr) {
		if (AddressValidator.addressIsInvalid(addr)) return ReceiptPrinterProperties.getProperty("address.outcome.deleted_is_invalid");
		if (addressExistsInReceipt(addr)) return ReceiptPrinterProperties.getProperty("address.outcome.address_exists_in_receipt");
		DeleteDao modifyAddressDao = new DeleteDao();
		FetchDao<Address> fetchAddressDao = new FetchDao<Address>();
		String recordToDelete = new ConvertToStringRecordAddress(addr).build();
		try {
			List<Address> addresses = fetchAddressDao.fetchAll(FilePaths.ADDRESS_DATA.getPath(), new ConvertFieldsToAddress());
			boolean addressNotFound = true;
			for (Address a : addresses) {
				if (a.getName().equals(addr.getName())) {
					addressNotFound = false;
					break;
				}
			}
			if (addressNotFound) { return ReceiptPrinterProperties.getProperty("address.outcome.not_found"); }
			modifyAddressDao.delete(FilePaths.ADDRESS_DATA.getPath(), recordToDelete);
			return ReceiptPrinterProperties.getProperty("address.outcome.deleted");
		} catch (IOException ioe) {
			ReceiptPrinterLogger.logMessage(this.getClass(), Level.SEVERE, "IOException while deleting address " + addr, ioe);
			return ReceiptPrinterProperties.getProperty("address.outcome.deleted_error");
		}
	}

	/**
	 * Checks to see if the Address to delete exists in a Receipt
	 * @param addr The Address that is desired to be deleted
	 * @return True if the Address exists in the Receipt data file; false otherwise
	 */
	boolean addressExistsInReceipt(Address addr) {
		ReceiptFetchProcess receiptFetch = new ReceiptFetchProcess();
		List<Receipt> receipts = receiptFetch.fetchReceipts();
		for (Receipt receipt : receipts) {
			if (receipt.getAddress().equals(addr)) {
				return true;
			}
		}				
		return false;
	}
}
