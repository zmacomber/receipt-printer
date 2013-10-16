package com.mbc.receiptprinter.bean;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ReceiptTest {

	@Test
	public void testCompareTo_ReceiptDate() {
		Receipt receipt1 = Receipt.newInstance("01/01/2000", getTestAddress(), getTestDesignation(), "100.00", "Test Notes");
		Receipt receipt2 = Receipt.newInstance("01/01/1999", getTestAddress(), getTestDesignation(), "100.00", "Test Notes");
		assertTrue(receiptsAreSortingCorrectly(receipt1, receipt2));
	}
	
	@Test
	public void testCompareTo_AddressName() {
		Receipt receipt1 = Receipt.newInstance("01/01/2000", getTestAddress(), getTestDesignation(), "100.00", "Test Notes");
		Receipt receipt2 = Receipt.newInstance("01/01/2000", getTestAddress(), getTestDesignation(), "100.00", "Test Notes");
		assertTrue(receiptsAreSortingCorrectly(receipt1, receipt2));
	}
	
	@Test
	public void testCompareTo_Designation() {
		Receipt receipt1 = Receipt.newInstance("01/01/2000", getTestAddress(), getTestDesignation(), "100.00", "Test Notes");
		Receipt receipt2 = Receipt.newInstance("01/01/2000", getTestAddress(), getTestDesignation(), "100.00", "Test Notes");
		assertTrue(receiptsAreSortingCorrectly(receipt1, receipt2));
	}
	
	@Test
	public void testCompareTo_Amount() {
		Receipt receipt1 = Receipt.newInstance("01/01/2000", getTestAddress(), getTestDesignation(), "100.00", "Test Notes");
		Receipt receipt2 = Receipt.newInstance("01/01/2000", getTestAddress(), getTestDesignation(), "1000.00", "Test Notes");
		assertTrue(receiptsAreSortingCorrectly(receipt1, receipt2));
	}
	
	Address getTestAddress() {
		return Address.newInstance(0, "Test", "Test address 1", "", "Test city", "ME", "12345");
	}
	
	Designation getTestDesignation() {
		return Designation.newInstance("Test");
	}
	
	boolean receiptsAreSortingCorrectly(Receipt receipt1, Receipt receipt2) {
		List<Receipt> receipts = new ArrayList<Receipt>();
		receipts.add(receipt1);
		receipts.add(receipt2);
		Collections.sort(receipts);
		if (receipts.get(0).equals(receipt1)) { 
			return true;
		}
		else { 
			return false;
		}
	}

}
