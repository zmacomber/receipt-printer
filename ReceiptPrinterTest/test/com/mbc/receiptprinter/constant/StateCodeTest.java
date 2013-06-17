package com.mbc.receiptprinter.constant;

import static org.junit.Assert.*;

import org.junit.Test;

public class StateCodeTest {
	
	@Test
	public void testGetCodeByName() {
		assertTrue(StateCode.getCodeByName("Maine") == "ME");
	}

	@Test
	public void testGetCodeByName_NullName() {
		assertTrue(StateCode.getCodeByName(null) == "");
	}
	
	@Test
	public void testGetCodeByName_EmptyName() {
		assertTrue(StateCode.getCodeByName("") == "");
	}
	
	@Test
	public void testGetCodeByName_InvalidName() {
		assertTrue(StateCode.getCodeByName("a;ldfjasfnlsnf;lasdnf;lasnf") == "");
	}
}
