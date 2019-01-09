package com.mbc.receiptprinter.address.process;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mbc.receiptprinter.address.AddressBaseTest;
import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.process.address.AddressAppendProcess;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;

public class AddressAppendProcessTest extends AddressBaseTest {
	
	private AddressAppendProcess process;

	@Before
	public void setUp() throws Exception {
		process = new AddressAppendProcess();
	}

	@Test
	public void testAppendAddress() {
		Address addr = getTestAddress();
		assertEquals(process.appendAddress(addr), ReceiptPrinterProperties.getProperty("address.outcome.added"));
	}
	
	@Test
	public void testAppendAddress_InvalidAddress() {
		Address addr = getTestEmptyAddress();
		assertEquals(process.appendAddress(addr), ReceiptPrinterProperties.getProperty("address.outcome.added_not_filled_in"));
	}
	
	@Test
	public void testAppendAddress_AddressAlreadyExists() {
		Address addr = getTestAddress();
		process.appendAddress(addr);
		assertEquals(process.appendAddress(addr), ReceiptPrinterProperties.getProperty("address.outcome.already_exists"));
	}

}
