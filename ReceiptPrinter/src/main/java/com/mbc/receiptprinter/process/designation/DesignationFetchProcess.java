package com.mbc.receiptprinter.process.designation;

import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.converter.ConvertFieldsToDesignation;
import com.mbc.receiptprinter.dao.FetchDao;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;
import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

/**
 * Responsible for the fetching of Designations
 */
public class DesignationFetchProcess {

    private List<Designation> designations;

    public DesignationFetchProcess() {
        designations = new ArrayList<Designation>();
    }

	/**
	 * Fetches a Designation by the Designation name
	 * @param name The name of the Designation
	 * @return The Designation that contains the given name; otherwise null
	 */
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
	
	/**
	 * Fetches all Designation records
	 * @return A List of all of the Designation records in the Designation data file; otherwise an empty list 
	 */
	public List<Designation> fetchDesignations() {

		if (designations.size() == 0) {
            FetchDao<Designation> designationFetchDao = new FetchDao<Designation>();
            try {
                designations = designationFetchDao.fetchAll(FilePaths.DESIGNATION_DATA.getPath(),
                                                            new ConvertFieldsToDesignation());
            } catch (IOException e) {
                ReceiptPrinterLogger.logMessage(this.getClass(), Level.SEVERE, "IOException while fetching designations", e);
            }
        }
		return designations;
	}
	
	/**
	 * Formats the Designation records in such a way as to be able to display them in a table on the user interface
	 * @return A two dimensional Object array of Designation data that is used for display purposes
	 */
	public Object[][] getDesignationData() {
		List<Designation> d = fetchDesignations();
		Collections.sort(d);
		Object[][] data = new Object[d.size()][1];
		int counter = 0;
		for (Designation designation : d) {
			data[counter][0] = designation.getName();
			counter++;
		}
		return data;
	}
	
	/**
	 * Used for populating receipt designation combo boxes on the user interface
	 * @return A String array of designations formatted for display on the receipt page
	 */
	public String[] getDesignationNames() {
		List<Designation> d = fetchDesignations();
		Collections.sort(d);
		if (d.size() == 0) return new String[] { "" };
		String[] designationNames = new String[d.size()];
		int counter = 0;
		for (Designation designation : d) {
			designationNames[counter] = designation.getName();
			counter++;
		}
		return designationNames;
	}
}