package com.mbc.receiptprinter.test;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.converter.ConvertFieldsToAddress;
import com.mbc.receiptprinter.converter.ConvertFieldsToDesignation;
import com.mbc.receiptprinter.converter.ConvertFieldsToReceipt;
import com.mbc.receiptprinter.converter.ConvertToStringRecordAddress;
import com.mbc.receiptprinter.converter.ConvertToStringRecordDesignation;
import com.mbc.receiptprinter.converter.ConvertToStringRecordReceipt;
import com.mbc.receiptprinter.dao.DeleteDao;
import com.mbc.receiptprinter.dao.FetchDao;


public class TestUtils {

	public static void deleteAllData() throws Exception {
		deleteAllReceipts();
		deleteAllDesignations();
		deleteAllAddresses();
	}
	
	static void deleteAllReceipts() throws Exception {
		FetchDao<Receipt> fetchDao = new FetchDao<Receipt>();
		DeleteDao deleteDao = new DeleteDao();
		String dataFilePath = FilePaths.RECEIPT_DATA_PATH;
		for (Receipt receipt : fetchDao.fetchAll(dataFilePath, new ConvertFieldsToReceipt())) {
			deleteDao.delete(dataFilePath, new ConvertToStringRecordReceipt(receipt).build());
		}
	}
	
	static void deleteAllDesignations() throws Exception {
		FetchDao<Designation> fetchDao = new FetchDao<Designation>();
		DeleteDao deleteDao = new DeleteDao();
		String dataFilePath = FilePaths.DESIGNATION_DATA_PATH;
		for (Designation designation : fetchDao.fetchAll(dataFilePath, new ConvertFieldsToDesignation())) {
			deleteDao.delete(dataFilePath, new ConvertToStringRecordDesignation(designation).build());
		}
	}
	
	static void deleteAllAddresses() throws Exception {
		FetchDao<Address> fetchDao = new FetchDao<Address>();
		DeleteDao deleteDao = new DeleteDao();
		String dataFilePath = FilePaths.ADDRESS_DATA_PATH;
		for (Address addr : fetchDao.fetchAll(dataFilePath, new ConvertFieldsToAddress())) {
			deleteDao.delete(dataFilePath, new ConvertToStringRecordAddress(addr).build());
		}
	}
}
