package com.mbc.receiptprinter.process.receipt;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.process.address.AddressAppendProcess;
import com.mbc.receiptprinter.process.designation.DesignationAppendProcess;
import com.mbc.receiptprinter.receipt.ReceiptBaseTest;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;

public class ReceiptDeleteProcessTest extends ReceiptBaseTest {

	private ReceiptDeleteProcess deleteProcess;
	private ReceiptAppendProcess appendProcess;
	
	private AddressAppendProcess aap;
	private DesignationAppendProcess dap;
	
	@Before
	public void setUp() throws Exception {
		deleteProcess = new ReceiptDeleteProcess();
		appendProcess = new ReceiptAppendProcess();
		
		aap = new AddressAppendProcess();
		dap = new DesignationAppendProcess();
	}

	@Test
	public void testDeleteReceipt() {
		Address addr = getTestAddress();
		aap.appendAddress(addr);
		
		Designation designation = getTestDesignation();
		dap.appendDesignation(designation);
		
		Receipt receipt = getTestReceipt(addr, designation);
		appendProcess.appendReceipt(receipt);
		assertEquals(ReceiptPrinterProperties.getProperty("receipt.outcome.deleted"), deleteProcess.deleteReceipt(receipt));
	}
	
	@Test
	public void testDeleteReceipt_InvalidReceipt() {
		Receipt receipt = getTestEmptyReceipt();
		assertEquals(ReceiptPrinterProperties.getProperty("receipt.outcome.deleted_is_invalid"), deleteProcess.deleteReceipt(receipt));
	}
	
	@Test
	public void testDeleteReceipt_NotFound() {
		Address addr = getTestAddress();
		aap.appendAddress(addr);
		
		Designation designation = getTestDesignation();
		dap.appendDesignation(designation);
		
		Receipt receipt = getTestReceipt(addr, designation);
		assertEquals(ReceiptPrinterProperties.getProperty("receipt.outcome.not_found"), deleteProcess.deleteReceipt(receipt));
	}

}
