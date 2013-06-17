import com.mbc.receiptprinter.constant.FileDelimiters;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.util.ReceiptPrinterFileUtils;

import java.io.File;
import java.util.List;

/*
* Used to add address ids to the existing address and receipt data files.
* This should only need to be run in case of using the file format
* that does not contain address ids.
*
* Should be compiled like this...javac -cp ReceiptPrinter.jar AddAddressIdsToDataFiles.java
* and run like this...java -cp .;ReceiptPrinter.jar AddAddressIdsToDataFiles
*/
public class AddAddressIdsToDataFiles {

	public static void main (String[] args) throws Exception {
		final String originalAddressFileContents = ReceiptPrinterFileUtils.getFileContents(FilePaths.ADDRESS_DATA_PATH);
		final String originalReceiptFileContents = ReceiptPrinterFileUtils.getFileContents(FilePaths.RECEIPT_DATA_PATH);
		backupDataFiles(originalAddressFileContents, originalReceiptFileContents);
		final String newAddressFileContents = addAddressIdsToAddressFile(originalAddressFileContents);
		addAddressIdsToReceiptFile(newAddressFileContents, originalReceiptFileContents);
	}

	private static void backupDataFiles(final String originalAddressFileContents, final String originalReceiptFileContents) throws Exception {
		File addressDataFile = new File(FilePaths.ADDRESS_DATA_PATH + ".bak");
		ReceiptPrinterFileUtils.writeStringToFile(addressDataFile, originalAddressFileContents);

		File receiptDataFile = new File(FilePaths.RECEIPT_DATA_PATH + ".bak");
		ReceiptPrinterFileUtils.writeStringToFile(receiptDataFile, originalReceiptFileContents);
	}

	private static String addAddressIdsToAddressFile(final String originalAddressFileContents) throws Exception {
		long id = 1;
		List<String> addressRecordList = ReceiptPrinterFileUtils.convertFileContentsToList(originalAddressFileContents);
		StringBuilder newAddressFileContents = new StringBuilder();
		for (String addressRecord : addressRecordList) {
			newAddressFileContents.append(id);
			newAddressFileContents.append(FileDelimiters.FIELD);
			newAddressFileContents.append(addressRecord);
			newAddressFileContents.append(FileDelimiters.RECORD);
			id++;
		}
		File addressDataFile = new File(FilePaths.ADDRESS_DATA_PATH);
		ReceiptPrinterFileUtils.writeStringToFile(addressDataFile, newAddressFileContents.toString());
		return newAddressFileContents.toString();
	}

	private static void addAddressIdsToReceiptFile(final String newAddressFileContents, final String originalReceiptFileContents) throws Exception {	
		List<String> receiptRecordList = ReceiptPrinterFileUtils.convertFileContentsToList(originalReceiptFileContents);
		List<String> addressRecordList = ReceiptPrinterFileUtils.convertFileContentsToList(newAddressFileContents);

		StringBuilder newReceiptFileContents = new StringBuilder();
		for (String receiptRecord : receiptRecordList) {
			String newReceiptRecord = buildNewReceiptRecord(receiptRecord.split(FileDelimiters.FIELD), addressRecordList);
			newReceiptFileContents.append(newReceiptRecord);
			newReceiptFileContents.append(FileDelimiters.RECORD);
		}
		File receiptDataFile = new File(FilePaths.RECEIPT_DATA_PATH);
		ReceiptPrinterFileUtils.writeStringToFile(receiptDataFile, newReceiptFileContents.toString());
	}

	private static String buildNewReceiptRecord(final String[] oldReceiptRecordFields, final List<String> addressRecordList) throws Exception {
		for (String addressRecord : addressRecordList) {
			String[] addressFields = addressRecord.split(FileDelimiters.FIELD);
			if (oldReceiptRecordFields[1].equals(addressFields[1])) {
				StringBuilder newReceiptRecord = new StringBuilder();
				newReceiptRecord.append(oldReceiptRecordFields[0]);
				newReceiptRecord.append(FileDelimiters.FIELD);
				newReceiptRecord.append(addressFields[0]);
				newReceiptRecord.append(FileDelimiters.FIELD);
				newReceiptRecord.append(oldReceiptRecordFields[2]);
				newReceiptRecord.append(FileDelimiters.FIELD);
				newReceiptRecord.append(oldReceiptRecordFields[3]);
				newReceiptRecord.append(FileDelimiters.FIELD);
				newReceiptRecord.append(oldReceiptRecordFields[4]);
				return newReceiptRecord.toString();
			}
		}
		throw new Exception("Match could not be found for " + oldReceiptRecordFields[1]);
	}
}