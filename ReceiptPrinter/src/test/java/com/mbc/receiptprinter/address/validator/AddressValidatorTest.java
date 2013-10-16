package com.mbc.receiptprinter.address.validator;

import com.mbc.receiptprinter.address.AddressBaseTest;
import com.mbc.receiptprinter.validator.AddressValidator;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressValidatorTest extends AddressBaseTest {

	@Test
	public void testAddressIsInvalid() {
		assertFalse(AddressValidator.addressIsInvalid(getTestAddress()));
	}
	
	@Test
	public void testAddressIsInvalid_NullAddress() {
		assertTrue(AddressValidator.addressIsInvalid(null));
	}
	
	@Test
	public void testAddressIsInvalid_EmptyAddress() {
		assertTrue(AddressValidator.addressIsInvalid(getTestEmptyAddress()));
	}
}
