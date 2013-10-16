package com.mbc.receiptprinter.process.address;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mbc.receiptprinter.bean.Address;

public class AddressProcessUtilTest {

	private Address testAddress;
	
	@Before
	public void setUp() throws Exception {
		testAddress = Address.newInstance(0, "Midcoast Baptist Church", "170 Old Portland Rd", "", "Brunswick", "ME", "04011");
	}

	@Test
	public void testGetAddressForReceipt() {
		String receiptAddress = AddressProcessUtil.getAddressForReceipt(testAddress);
		assertEquals(receiptAddress, "Midcoast Baptist Church (170 Old Portland Rd, Brunswick, ME)");
	}

	@Test
	public void testExtractNameFromReceiptAddress() {
		String receiptAddress = AddressProcessUtil.getAddressForReceipt(testAddress);
		String name = AddressProcessUtil.extractNameFromReceiptAddress(receiptAddress);
		assertEquals(name, "Midcoast Baptist Church");
	}

	@Test
	public void testExtractAddress1FromReceiptAddress() {
		String receiptAddress = AddressProcessUtil.getAddressForReceipt(testAddress);
		String address1 = AddressProcessUtil.extractAddress1FromReceiptAddress(receiptAddress);
		assertEquals(address1, "170 Old Portland Rd");
	}

	@Test
	public void testExtractCityFromReceiptAddress() {
		String receiptAddress = AddressProcessUtil.getAddressForReceipt(testAddress);
		String city = AddressProcessUtil.extractCityFromReceiptAddress(receiptAddress);
		assertEquals(city, "Brunswick");
	}

	@Test
	public void testExtractStateCodeFromReceiptAddress() {
		String receiptAddress = AddressProcessUtil.getAddressForReceipt(testAddress);
		String stateCode = AddressProcessUtil.extractStateCodeFromReceiptAddress(receiptAddress);
		assertEquals(stateCode, "ME");
	}

}
