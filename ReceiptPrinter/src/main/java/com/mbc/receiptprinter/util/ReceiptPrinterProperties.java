package com.mbc.receiptprinter.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.Properties;

import com.mbc.receiptprinter.constant.FilePaths;

public class ReceiptPrinterProperties {

	private static final Properties props;

	static {
		props = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(FilePaths.APPLICATION_PROPERTIES.getPath());
			props.load(in);
		}
		catch (Exception e) {
			// Log Exception and then exit out as this would be a serious error and should never happen...
			ReceiptPrinterLogger.logMessage(ReceiptPrinterProperties.class,
							Level.SEVERE,
							"Exception loading " + FilePaths.APPLICATION_PROPERTIES.getPath() + ".  Exiting application.",
							e);
			System.exit(0);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) { }
			}
		}				
	}

	public static final String getProperty(String key) {
		return props.getProperty(key);
	}
}
