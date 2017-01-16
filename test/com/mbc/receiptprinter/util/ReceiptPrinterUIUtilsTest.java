package com.mbc.receiptprinter.util;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Test;

public class ReceiptPrinterUIUtilsTest {

	@Test
	public void testGetMainFrame() {
		JFrame frame = new JFrame("test");
        frame.setName("test");
        assertNull(ReceiptPrinterUIUtils.getMainFrame());
	}

}
