package com.mbc.receiptprinter.process.designation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.designation.DesignationBaseTest;
import com.mbc.receiptprinter.process.address.AddressAppendProcess;
import com.mbc.receiptprinter.process.address.AddressFetchProcess;
import com.mbc.receiptprinter.process.receipt.ReceiptAppendProcess;
import com.mbc.receiptprinter.process.receipt.ReceiptFetchProcess;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;

public class DesignationDeleteProcessTest extends DesignationBaseTest{

	DesignationDeleteProcess ddp;
	DesignationAppendProcess dap;
	
	ReceiptAppendProcess rap;
	ReceiptFetchProcess rfp;
	
	AddressFetchProcess afp;
	AddressAppendProcess aap;
	
	@Before
	public void setUp() throws Exception {
		ddp = new DesignationDeleteProcess();
		dap = new DesignationAppendProcess();
		dap.appendDesignation(getTestDesignation());
		
		aap = new AddressAppendProcess();
		aap.appendAddress(getTestAddress());
		
		rap = new ReceiptAppendProcess();
		rap.appendReceipt(getTestReceipt());
	}
	
	public Receipt getTestReceipt() {
		return Receipt.newInstance("01/01/2013", getTestAddress(), getTestDesignation(), "100.00", "Test notes");
	}
	
	public Address getTestAddress() {
		return Address.newInstance(1, "Test", "Test address 1", "", "Test City", "ME", "04011");
	}
	
	@Test
	public void testDesignationExistsInReceipt() {
		assertTrue(ddp.designationExistsInReceipt(getTestDesignation()));
	}
	
	@Test
	public void testDesignationExistsInReceipt_False() {
		assertFalse(ddp.designationExistsInReceipt(getTestDesignation2()));
	}

	@Test
	public void testDeleteDesignation() {
		dap.appendDesignation(getTestDesignation2());
		String outcome = ddp.deleteDesignation(getTestDesignation2());
		assertEquals(ReceiptPrinterProperties.getProperty("designation.outcome.deleted"), outcome);
	}
	
	@Test
	public void testDeleteDesignation_InvalidDesignation() {
		String outcome = ddp.deleteDesignation(null);
		assertEquals(ReceiptPrinterProperties.getProperty("designation.outcome.deleted_is_invalid"), outcome);
	}
	
	@Test
	public void testDeleteDesignation_ExistsInReceipt() {
		String outcome = ddp.deleteDesignation(getTestDesignation());
		assertEquals(ReceiptPrinterProperties.getProperty("designation.outcome.designation_exists_in_receipt"), outcome);
	}
	
	@Test
	public void testDeleteDesignation_NotFound() {
		String outcome = ddp.deleteDesignation(getTestDesignation2());
		assertEquals(ReceiptPrinterProperties.getProperty("designation.outcome.not_found"), outcome);
	}
}
