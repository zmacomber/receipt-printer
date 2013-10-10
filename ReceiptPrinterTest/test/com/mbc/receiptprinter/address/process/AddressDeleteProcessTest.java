package com.mbc.receiptprinter.address.process;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mbc.receiptprinter.address.AddressBaseTest;
import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.process.address.AddressAppendProcess;
import com.mbc.receiptprinter.process.address.AddressDeleteProcess;
import com.mbc.receiptprinter.process.address.AddressFetchProcess;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;

public class AddressDeleteProcessTest extends AddressBaseTest{

	private AddressDeleteProcess deleteProcess;
	private AddressAppendProcess appendProcess;
	private AddressFetchProcess fetchProcess;
	
	@Before
	public void setUp() throws Exception {
		deleteProcess = new AddressDeleteProcess();
		appendProcess = new AddressAppendProcess();
		fetchProcess = new AddressFetchProcess();
	}

	@Test
	public void testDeleteAddress() {
		Address addr = getTestAddress();
		appendProcess.appendAddress(addr);
		int originalAddressCount = fetchProcess.fetchAddresses().size();
		
		String outcome = deleteProcess.deleteAddress(addr);
		int newAddressCount = fetchProcess.fetchAddresses().size();
		
		assertEquals(newAddressCount, originalAddressCount - 1);
		assertEquals(outcome, ReceiptPrinterProperties.getProperty("address.outcome.deleted"));
	}
	
	@Test
	public void testDeleteAddress_AddressNotFound() {
		Address addr = getTestAddress();
		appendProcess.appendAddress(addr);
				
		String outcome = deleteProcess.deleteAddress(getTestAddress2());
				
		assertEquals(outcome, ReceiptPrinterProperties.getProperty("address.outcome.not_found"));
	}
	
	@Test
	public void testDeleteAddress_InvalidAddress() {
		assertEquals(ReceiptPrinterProperties.getProperty("address.outcome.deleted_is_invalid"), deleteProcess.deleteAddress(getTestEmptyAddress()));
	}

}
