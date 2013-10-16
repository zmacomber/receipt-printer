package com.mbc.receiptprinter.address.dao;

import com.mbc.receiptprinter.address.AddressBaseTest;
import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.converter.ConvertToStringRecordAddress;
import com.mbc.receiptprinter.dao.AppendDao;
import com.mbc.receiptprinter.test.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertTrue;

public class AddressDaoStressTest extends AddressBaseTest {
	
	private AppendDao add;

	@Before
	public void setUp() throws Exception {
		 add = new AppendDao();
	}

	@Test
	public void stressTest() throws Exception {
		
		Calendar start = Calendar.getInstance();
		
		System.out.println(start.getTime());
		
		for (int x = 0; x < 1000; x++) {
			
			// Insert a new address
			Address testAddress = getTestAddress();
			add.append(FilePaths.ADDRESS_DATA.getPath(),
					   new ConvertToStringRecordAddress(testAddress));
		}
		
		System.out.println("Deleting addresses");

		// Delete all data
		TestUtils.deleteAllData();
		
		Calendar end = Calendar.getInstance();
		
		System.out.println(end.getTime());
		
		// Assert time elapsed is less than or equal to 1 minute
		assertTrue((end.get(Calendar.MINUTE) - start.get(Calendar.MINUTE)) <= 1);
	}
}
