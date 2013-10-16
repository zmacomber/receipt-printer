package com.mbc.receiptprinter.address.process;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mbc.receiptprinter.address.AddressBaseTest;
import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.process.address.AddressProcessUtil;

public class AddressProcessUtilTest extends AddressBaseTest {

	@Test
	public void testAddressAlreadyExists_true() {
		Address addr = getTestAddress();
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(addr);
		
		assertTrue(AddressProcessUtil.addressAlreadyExists(addr, addresses));
	}
	
	@Test
	public void testAddressAlreadyExists_false() {
		Address addr = getTestAddress();
		Address addr2 = getTestAddress2();
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(addr);
		
		assertFalse(AddressProcessUtil.addressAlreadyExists(addr2, addresses));
	}
	
	@Test
	public void testAddressAlreadyExists_emptyList() {
		assertFalse(AddressProcessUtil.addressAlreadyExists(getTestAddress(), new ArrayList<Address>()));
	}
	
	@Test
	public void testAddressAlreadyExists_InvalidList() {
		assertFalse(AddressProcessUtil.addressAlreadyExists(getTestAddress(), null));
	}
	
	@Test
	public void testAddressAlreadyExists_InvalidAddress() {
		assertFalse(AddressProcessUtil.addressAlreadyExists(null, new ArrayList<Address>()));
	}
	
	@Test
	public void testExtractsFromReceiptAddress() {
		String goodReceiptAddress = "Address name (some address 1, some city, some stateCode)";
		
		assertNotNull(AddressProcessUtil.extractNameFromReceiptAddress(goodReceiptAddress));
		assertNotNull(AddressProcessUtil.extractAddress1FromReceiptAddress(goodReceiptAddress));
		assertNotNull(AddressProcessUtil.extractCityFromReceiptAddress(goodReceiptAddress));
		assertNotNull(AddressProcessUtil.extractStateCodeFromReceiptAddress(goodReceiptAddress));
	}
	
	@Test
	public void testExtractsFromReceiptAddress_NullTest() {
		String badReceiptAddress = "Address name - some address 1 | some city | some stateCode";
		
		assertNull(AddressProcessUtil.extractNameFromReceiptAddress(badReceiptAddress));
		assertNull(AddressProcessUtil.extractAddress1FromReceiptAddress(badReceiptAddress));
		assertNull(AddressProcessUtil.extractCityFromReceiptAddress(badReceiptAddress));
		assertNull(AddressProcessUtil.extractStateCodeFromReceiptAddress(badReceiptAddress));
	}
}
