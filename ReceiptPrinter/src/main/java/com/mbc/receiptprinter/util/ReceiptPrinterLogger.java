package com.mbc.receiptprinter.util;

import com.mbc.receiptprinter.constant.FilePaths;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/*
 * Core class for logging in ReceiptPrinter
 */
public class ReceiptPrinterLogger {
		
	public static void logMessage(Class<?> c, Level loggingLevel, String message) {
		if (message != null) {
			Logger logger = setupLogger(c, loggingLevel);
			logger.log(loggingLevel, message);
		}
	}
	
	public static void logMessage(Class<?> c, Level loggingLevel, String message, Exception ex) {
		if ((message != null) && (ex != null)) {
			Logger logger = setupLogger(c, loggingLevel);
			logger.log(loggingLevel, message, ex);
		}
	}

	static Logger setupLogger(Class<?> c, Level loggingLevel) {
		Logger logger = (c == null) ? Logger.getLogger("ReceiptPrinter") : Logger.getLogger(c.getName());
		logger.setLevel((loggingLevel == null) ? Level.INFO : loggingLevel);
		logger.addHandler(getFileHandler());
		return logger;
	}

	static FileHandler getFileHandler() {
		FileHandler fileHandler = null;
		try {
			FileUtils.touch(new File(FilePaths.LOG.getPath()));
			fileHandler = new FileHandler(FilePaths.LOG.getPath(), true);
			fileHandler.setFormatter(new SimpleFormatter());
		} catch (IOException e) {
			// If this occurs, there are serious issues in the application - exit out
			System.exit(0);
		}
		return fileHandler;
	}
}
