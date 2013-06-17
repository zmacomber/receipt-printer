package com.mbc.receiptprinter.process.designation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.designation.DesignationBaseTest;

public class DesignationFetchProcessTest extends DesignationBaseTest {

	private DesignationFetchProcess fetchProcess;
	private DesignationAppendProcess appendProcess;
	private Designation designation;
	
	@Before
	public void setUp() throws Exception {
		designation = getTestDesignation();
		fetchProcess = new DesignationFetchProcess();
		appendProcess = new DesignationAppendProcess();
		appendProcess.appendDesignation(designation);
	}

	@Test
	public void testFetchDesignation() {
		assertNotNull(fetchProcess.fetchDesignation(designation.getName()));
	}
	
	@Test
	public void testFetchDesignation_Null() {
		assertNull(fetchProcess.fetchDesignation(""));
	}

	@Test
	public void testFetchDesignations() {
		assertEquals(fetchProcess.fetchDesignations().size(), 1);
	}

	@Test
	public void testGetAddressData() {
		Object data[][] = fetchProcess.getAddressData();
		assertEquals(data[0][0], designation.getName());
	}

	@Test
	public void testGetDesignationNames() {
		Designation designation2 = getTestDesignation2();
		appendProcess.appendDesignation(designation2);
		String[] names = fetchProcess.getDesignationNames();
		assertEquals(names[0], designation.getName());
		assertEquals(names[1], designation2.getName());	
	}
	
	@Test
	public void testGetDesignationNames_Null() throws Exception {
		DesignationDeleteProcess ddp = new DesignationDeleteProcess();
		ddp.deleteDesignation(getTestDesignation());
		String[] names = fetchProcess.getDesignationNames(); 
		assertEquals(names[0], "");	
	}
}
