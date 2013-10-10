package com.mbc.receiptprinter.dao;

import com.mbc.receiptprinter.constant.FileDelimiters;
import com.mbc.receiptprinter.converter.ConvertFields;
import com.mbc.receiptprinter.util.ReceiptPrinterFileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Fetches records from a data file
 * @param <T> The type of records to fetch (i.e. Address)
 */
public class FetchDao<T> {

    private static int FETCH_ALL_COUNTER = 0;

	/**
	 * Fetches all records from a data file
	 * @param dataFilePath The file path of the data file
	 * @param convertFields {@link ConvertFields}
	 * @return A List of data bean (i.e. Address) objects
	 * @throws IOException Possibly thrown when getFileContents is called
	 */
	public List<T> fetchAll(String dataFilePath, ConvertFields<T> convertFields) throws IOException {
		String fileContents = ReceiptPrinterFileUtils.getFileContents(dataFilePath);
		List<String> records = ReceiptPrinterFileUtils.convertFileContentsToList(fileContents);
		List<T> fetchedList = new ArrayList<T>();
		for (String record : records) {
			String[] fields = convertFields.hasMultipleFields() ? record.split(FileDelimiters.FIELD) : new String[] { record };
			fetchedList.add(convertFields.convert(fields));
		}

        FETCH_ALL_COUNTER++;
        System.out.println("FetchDao.fetchAll() has been called " + FETCH_ALL_COUNTER + " times");

        return fetchedList;
	}
}
