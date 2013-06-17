package com.mbc.receiptprinter.process.designation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.converter.ConvertFieldsToDesignation;
import com.mbc.receiptprinter.dao.FetchDao;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;
import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

public class DesignationFetchProcess {
	public Designation fetchDesignation(String name) {
		if (ReceiptPrinterStringUtils.isNullOrEmpty(name)) return null;
		Designation fetched = null; 
		for (Designation designation : fetchDesignations()) {
			if (designation.getName().equals(name)) {
				fetched = designation;
				break;
			}
		}
		return fetched;
	}
	public List<Designation> fetchDesignations() {
		FetchDao<Designation> designationFetchDao = new FetchDao<Designation>();
		List<Designation> designations = new ArrayList<Designation>();
		try {
			designations = designationFetchDao.fetchAll(FilePaths.DESIGNATION_DATA_PATH,
														new ConvertFieldsToDesignation());
		} catch (IOException e) {
			ReceiptPrinterLogger.logMessage(this.getClass(), Level.SEVERE, "IOException while fetching designations", e);
		}
		return designations;
	}
	public Object[][] getAddressData() {
		List<Designation> designations = fetchDesignations();
		Collections.sort(designations);
		Object[][] data = new Object[designations.size()][1];
		int counter = 0;
		for (Designation designation : designations) {
			data[counter][0] = designation.getName();
			counter++;
		}
		return data;
	}
	public String[] getDesignationNames() {
		List<Designation> designations = fetchDesignations();
		Collections.sort(designations);
		if (designations.size() == 0) return new String[] { "" };
		String[] designationNames = new String[designations.size()];
		int counter = 0;
		for (Designation designation : designations) {
			designationNames[counter] = designation.getName();
			counter++;
		}
		return designationNames;
	}
}