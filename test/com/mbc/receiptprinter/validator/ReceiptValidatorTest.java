package com.mbc.receiptprinter.validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mbc.receiptprinter.receipt.ReceiptBaseTest;

public class ReceiptValidatorTest extends ReceiptBaseTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReceiptIsInvalid() {
		assertFalse(ReceiptValidator.receiptIsInvalid(getTestReceipt(getTestAddress(), getTestDesignation())));
	}
	
	@Test
	public void testReceiptIsInvalid_Null() {
		assertTrue(ReceiptValidator.receiptIsInvalid(null));
	}

	@Test
	public void testFieldsAreInvalid_False() {
		String fields[] = { "01/01/2000", "1", "Test Designation", "10.00", "Test Notes" };
		assertFalse(ReceiptValidator.fieldsAreInvalid(fields));
	}
	
	@Test
	public void testFieldsAreInvalid_True() {
		String fields[] = { "01/01/2000", "1", "Test Designation", "abcdefg", "Test Notes" };
		assertTrue(ReceiptValidator.fieldsAreInvalid(fields));
	}

}
