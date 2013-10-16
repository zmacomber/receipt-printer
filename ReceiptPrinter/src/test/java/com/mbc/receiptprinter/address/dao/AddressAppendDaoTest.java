package com.mbc.receiptprinter.address.dao;

import com.mbc.receiptprinter.address.AddressBaseTest;
import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.constant.FilePaths;
import com.mbc.receiptprinter.converter.ConvertFieldsToAddress;
import com.mbc.receiptprinter.converter.ConvertToStringRecordAddress;
import com.mbc.receiptprinter.dao.AppendDao;
import com.mbc.receiptprinter.dao.FetchDao;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddressAppendDaoTest extends AddressBaseTest {

	private AppendDao appendDao;
	private FetchDao<Address> fetchDao;

	@Before
	public void setupBefore() throws Exception {
		appendDao = new AppendDao();
		fetchDao = new FetchDao<Address>();
	}
	
	@Test
	public void testAppend() throws Exception {
		int startRecordCount = fetchDao.fetchAll(FilePaths.ADDRESS_DATA.getPath(),
				   								 new ConvertFieldsToAddress()).size();
		appendDao.append(FilePaths.ADDRESS_DATA.getPath(),
						 new ConvertToStringRecordAddress(getTestAddress()));
		int endRecordCount = fetchDao.fetchAll(FilePaths.ADDRESS_DATA.getPath(),
											   new ConvertFieldsToAddress()).size();
		assertEquals(endRecordCount, startRecordCount + 1);
	}

}
