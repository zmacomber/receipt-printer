package com.mbc.receiptprinter.util;

import com.mbc.receiptprinter.constant.FilePaths;
import org.apache.commons.io.FileUtils;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import static org.junit.Assert.assertNotNull;

public class ReceiptPrinterLoggerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		clearLogFile();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		clearLogFile();
	}
	
	static void clearLogFile() throws IOException {
		File log = new File(FilePaths.LOG.getPath());
		FileUtils.touch(log);
		FileUtils.writeStringToFile(log, "");
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
			
	@Test
	public void testGetFileHandler() throws Exception {
		FileHandler fh = ReceiptPrinterLogger.getFileHandler();
		assertNotNull(fh);
	}
			
	@Test
	public void testLogMessage() throws Exception {
		ReceiptPrinterLogger.logMessage(this.getClass(), Level.SEVERE, "Testing the logging of a severe message...");
	}
	
	@Test
	public void testLogMessage_WithException() throws Exception {
		Exception ex = new Exception("An issue has occurred...");
		ReceiptPrinterLogger.logMessage(this.getClass(), Level.SEVERE, "Testing the logging of a severe message...", ex);
	}
	
	@Test
	public void testLogMessage_WithAllNullParms() throws Exception {
		ReceiptPrinterLogger.logMessage(null, null, null, null);
	}

}
