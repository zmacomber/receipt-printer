package com.mbc.receiptprinter.util;

import org.junit.*;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

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
    @Ignore("Fix later...")
	public void testGetFileContents() throws Exception {
		String fileContents = ReceiptPrinterFileUtils.getFileContents("test.txt");
		assertNotNull(fileContents);
	}
	
	@Test(expected=IOException.class)
    @Ignore("Fix later...")
	public void testGetFileContents_BadFileName() throws Exception {
		ReceiptPrinterFileUtils.getFileContents("" + new Date());
	}

}
