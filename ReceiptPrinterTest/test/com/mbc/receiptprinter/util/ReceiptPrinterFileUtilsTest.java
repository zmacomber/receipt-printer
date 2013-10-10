package com.mbc.receiptprinter.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReceiptPrinterFileUtilsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFileContents() throws Exception {
		String fileContents = ReceiptPrinterFileUtils.getFileContents("test.txt");
		assertNotNull(fileContents);
	}
	
	@Test(expected=IOException.class)
	public void testGetFileContents_BadFileName() throws Exception {
		ReceiptPrinterFileUtils.getFileContents("" + new Date());
	}

}
