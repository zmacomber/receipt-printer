package com.mbc.receiptprinter.receipt;

import org.junit.After;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.test.TestUtils;

public class ReceiptBaseTest {

	@After
	public void tearDownAfter() throws Exception {
		TestUtils.deleteAllData();
	}
	
	public Receipt getTestReceipt(Address addr, Designation designation) {
		return Receipt.newInstance("01/01/2000", 
									addr,
									designation,
				   				   "200.00", 
				   				   "Hi");
	}
	
	public Receipt getTestReceipt2(Address addr, Designation designation) {
		return Receipt.newInstance("01/01/2000", 
								   addr,
								   designation,
								   "100.00", 
								   "Hi There");
	}
	
	public Receipt getTestReceipt3(Address addr, Designation designation) {
		return Receipt.newInstance("01/01/2013", 
								   addr,
								   designation,
								   "300.00", 
								   "2013 receipt...");
	}
	
	public Address getTestAddress() {
		return Address.newInstance(1, "Test", "Test address 1", "", "Test city", "ME", "12345");
	}
	
	public Designation getTestDesignation() {
		return Designation.newInstance("Test");
	}
	
	public Receipt getTestEmptyReceipt() {
		return Receipt.newInstance("", null, null, "", "");
	}
	
	public Receipt getTestNullFieldsAddress() {
		return Receipt.newInstance(null, null, null, null, null);
	}
}
