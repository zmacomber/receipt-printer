package com.mbc.receiptprinter.designation;

import org.junit.After;

import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.test.TestUtils;

public class DesignationBaseTest {
	
	@After
	public void tearDownAfter() throws Exception {
		TestUtils.deleteAllData();
	}
	
	public Designation getTestDesignation() {
		return Designation.newInstance("Designation 1");
	}
	
	public Designation getTestDesignation2() {
		return Designation.newInstance("Designation 2");
	}
	
	public Designation getTestEmptyDesignation() {
		return Designation.newInstance("");
	}
	
	public Designation getTestNullFieldsDesignation() {
		return Designation.newInstance(null);
	}
}
