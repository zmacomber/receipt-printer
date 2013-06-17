package com.mbc.receiptprinter.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReceiptPrinterStringUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsNullOrEmpty() {
		assertTrue(ReceiptPrinterStringUtils.isNullOrEmpty(" "));
		assertTrue(ReceiptPrinterStringUtils.isNullOrEmpty(""));
		assertTrue(ReceiptPrinterStringUtils.isNullOrEmpty(null));
	}

	@Test
	public void testIsNotNullOrEmpty() {
		assertTrue(ReceiptPrinterStringUtils.isNotNullOrEmpty("test"));
	}

}
