package com.mbc.receiptprinter.process.receipt;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.process.address.AddressAppendProcess;
import com.mbc.receiptprinter.process.address.AddressProcessUtil;
import com.mbc.receiptprinter.process.designation.DesignationAppendProcess;
import com.mbc.receiptprinter.receipt.ReceiptBaseTest;
import com.mbc.receiptprinter.ui.receipt.ReceiptTableModel;
import com.mbc.receiptprinter.ui.tabs.TotalYearlyAmountReportTab;
import com.mbc.receiptprinter.ui.totalyearlyamountreport.TotalYearlyAmountReportTableModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ReceiptFetchProcessTest extends ReceiptBaseTest {

	private ReceiptFetchProcess fetchProcess;
	private ReceiptAppendProcess appendProcess;
	private Address addr;
	private Designation designation;
	
	@Before
	public void setUp() throws Exception {
		fetchProcess = new ReceiptFetchProcess();
		appendProcess = new ReceiptAppendProcess();
		addr = getTestAddress();
		designation = getTestDesignation();
		
		AddressAppendProcess aap = new AddressAppendProcess();
		DesignationAppendProcess dap = new DesignationAppendProcess();
		
		aap.appendAddress(addr);
		dap.appendDesignation(designation);
	}

	@Test
	public void testFetchReceipt() {
		Receipt receipt = getTestReceipt(addr, designation);
		appendProcess.appendReceipt(receipt);
		assertNotNull(fetchProcess.fetchReceipt(receipt.getReceiptDate(), 
								  				receipt.getAddress(), 
								  				receipt.getDesignation(), 
								  				receipt.getAmount()));
	}
	
	@Test
	public void testFetchReceipt_Null() {
		Receipt receipt = getTestReceipt(addr, designation);
		Receipt receipt2 = getTestReceipt2(addr, designation);
		appendProcess.appendReceipt(receipt);
		assertNull(fetchProcess.fetchReceipt(receipt2.getReceiptDate(), 
								  			 receipt2.getAddress(), 
								  			 receipt2.getDesignation(), 
								  			 receipt2.getAmount()));
	}
	
	@Test
	public void testFetchReceipts() {
		Receipt receipt = getTestReceipt(addr, designation);
		appendProcess.appendReceipt(receipt);
		assertEquals(1, fetchProcess.fetchReceipts().size());
	}
	
	@Test
	public void testFetchReceipts_NoReceiptsOnFile() {
		assertEquals(0, fetchProcess.fetchReceipts().size());
	}
	
	@Test
	public void testGetReceiptData() {	
		Receipt receipt = getTestReceipt(addr, designation);
		appendProcess.appendReceipt(receipt);
		Object[][] data = ReceiptTableModel.getReceiptData(fetchProcess);
		assertEquals(data[0][0], receipt.getReceiptDate());
		assertEquals(data[0][1], AddressProcessUtil.getAddressForReceipt(receipt.getAddress()));
		assertEquals(data[0][2], receipt.getDesignation().getName());
		assertEquals(data[0][3], receipt.getAmount());
		assertEquals(data[0][4], receipt.getNotes());
	}
	
	@Test
	public void testGetYearlyReportData() throws Exception {
		Receipt receipt = getTestReceipt(addr, designation);
		Receipt receipt2 = getTestReceipt2(addr, designation);
		appendProcess.appendReceipt(receipt);
		appendProcess.appendReceipt(receipt2);
		Object[][] yearlyReportData = new TotalYearlyAmountReportTableModel("2000").getYearlyReportData();
		assertEquals("Test (Test address 1, Test city, ME)", yearlyReportData[0][0]);
		BigDecimal amount = new BigDecimal("300.00");
		assertEquals(amount.toPlainString(), ((BigDecimal)yearlyReportData[0][1]).toPlainString());
	}
	
	@Test
	public void testGetReceiptYears() throws Exception {
		Receipt receipt = getTestReceipt(addr, designation);
		Receipt receipt2 = getTestReceipt2(addr, designation);
		Receipt receipt3 = getTestReceipt3(addr, designation);
		appendProcess.appendReceipt(receipt);
		appendProcess.appendReceipt(receipt2);
		appendProcess.appendReceipt(receipt3);
		String[] receiptYears = new TotalYearlyAmountReportTab().getReceiptYearsArr();
		assertEquals("2013", receiptYears[0]);
		assertEquals("2000", receiptYears[1]);
	}
}
