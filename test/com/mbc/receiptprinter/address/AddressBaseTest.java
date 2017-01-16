package com.mbc.receiptprinter.address;

import org.junit.After;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.constant.StateCode;
import com.mbc.receiptprinter.test.TestUtils;

public class AddressBaseTest {
	
	@After
	public void tearDownAfter() throws Exception {
		TestUtils.deleteAllData();
	}
	
	public Address getTestAddress() {
		
		return Address.newInstance(1,
									"Midcoast Baptist Church", 
									"170 Old Portland Road",
									"",
									"Brunswick", 
									StateCode.ME.toString(), 
									"04011");
	}
	
	public Address getTestAddress2() {
		
		return Address.newInstance(2,
									"Home", 
									"57 Bridge Road",
									"",
									"Brunswick", 
									StateCode.ME.toString(), 
									"04011");
	}
	
	public Address getTestEmptyAddress() {
		return Address.newInstance(0, "", "", "", "", "", "");
	}
	
	public Address getTestNullFieldsAddress() {
		return Address.newInstance(0, null, null, null, null, null, null);
	}
}
