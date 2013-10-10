package com.mbc.receiptprinter.address.process;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mbc.receiptprinter.address.AddressBaseTest;
import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.process.address.AddressAppendProcess;
import com.mbc.receiptprinter.process.address.AddressFetchProcess;

public class AddressFetchProcessTest extends AddressBaseTest {

	private AddressFetchProcess fetchProcess;
	private AddressAppendProcess appendProcess;
	
	@Before
	public void setUp() throws Exception {
		fetchProcess = new AddressFetchProcess();
		appendProcess = new AddressAppendProcess();
	}

	@Test
	public void testFetchAddress() {
		Address addr = getTestAddress();
		appendProcess.appendAddress(addr);
		Address fetchedAddr = fetchProcess.fetchAddress(addr.getId()); 
		assertEquals(fetchedAddr.getName(), addr.getName());
		assertEquals(fetchedAddr.getAddress1(), addr.getAddress1());
		assertEquals(fetchedAddr.getAddress2(), addr.getAddress2());
		assertEquals(fetchedAddr.getCity(), addr.getCity());
		assertEquals(fetchedAddr.getStateCode(), addr.getStateCode());
		assertEquals(fetchedAddr.getZipCode(), addr.getZipCode());
	}
	
	@Test
	public void testFetchAddressWithParams() {
		Address addr = getTestAddress();
		appendProcess.appendAddress(addr);
		Address fetchedAddr = fetchProcess.fetchAddress(addr.getName(), addr.getAddress1(), addr.getCity(), addr.getStateCode()); 
		assertEquals(fetchedAddr.getName(), addr.getName());
		assertEquals(fetchedAddr.getAddress1(), addr.getAddress1());
		assertEquals(fetchedAddr.getAddress2(), addr.getAddress2());
		assertEquals(fetchedAddr.getCity(), addr.getCity());
		assertEquals(fetchedAddr.getStateCode(), addr.getStateCode());
		assertEquals(fetchedAddr.getZipCode(), addr.getZipCode());
	}
	
	@Test
	public void testFetchAddress_InvalidId() {
		Address addr = getTestAddress();
		appendProcess.appendAddress(addr);
		Address fetchedAddr = fetchProcess.fetchAddress(-1); 
		assertNull(fetchedAddr);
	}
	
	@Test
	public void testFetchAddressFromReceipt() {
		String receiptAddress = "Midcoast Baptist Church (170 Old Portland Road, Brunswick, ME)";
		Address addr = getTestAddress();
		appendProcess.appendAddress(addr);
		Address fetchedAddr = fetchProcess.fetchAddressFromReceipt(receiptAddress); 
		
		assertEquals(fetchedAddr.getName(), addr.getName());
		assertEquals(fetchedAddr.getAddress1(), addr.getAddress1());
		assertEquals(fetchedAddr.getAddress2(), addr.getAddress2());
		assertEquals(fetchedAddr.getCity(), addr.getCity());
		assertEquals(fetchedAddr.getStateCode(), addr.getStateCode());
		assertEquals(fetchedAddr.getZipCode(), addr.getZipCode());
	}
	
	@Test
	public void testFetchAddresses() {
		Address addr = getTestAddress();
		appendProcess.appendAddress(addr);
		assertEquals(fetchProcess.fetchAddresses().size(), 1);
	}

	@Test
	public void testGetAddressData() {
		Address addr = getTestAddress();
		appendProcess.appendAddress(addr);
		Object[][] data = fetchProcess.getAddressData();
		assertEquals(data[0][0], addr.getName());
		assertEquals(data[0][1], addr.getAddress1());
		assertEquals(data[0][2], addr.getAddress2());
		assertEquals(data[0][3], addr.getCity());
		assertEquals(data[0][4], addr.getStateCode());
		assertEquals(data[0][5], addr.getZipCode());
	}
}
