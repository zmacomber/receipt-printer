package com.mbc.receiptprinter.bean;

import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

/**
* Bean for storing addresses
*/
public class Address implements Comparable<Address> {

	private final long id;
	private final String name;
	private final String address1;
	private final String address2;
	private final String city;
	private final String stateCode;
	private final String zipCode;
	
	private Address(long id, String name, String address1, String address2, String city, String stateCode, String zipCode) {
		this.id = id;
		this.name = name;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.stateCode = stateCode;
		this.zipCode = zipCode;
	}
	
	public static Address newInstance(long id, String name, String address1, String address2, String city, String stateCode, String zipCode) {
		return new Address(id, name, address1, address2, city, stateCode, zipCode);
	}

	public long getId()		{ return id;		}	
	public String getName() 	{ return name; 	   	}
	public String getAddress1() 	{ return address1; 	}
	public String getAddress2() 	{ return address2; 	}
	public String getCity() 	{ return city; 	   	}
	public String getStateCode() 	{ return stateCode;	}
	public String getZipCode() 	{ return zipCode;	}
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", address1=" + address1
				+ ", address2=" + address2 + ", city=" + city + ", stateCode="
				+ stateCode + ", zipCode=" + zipCode + "]";
	}

	/**
	* Comparing is done based on this order: name, city, state code, address1
	*/	
	@Override
	public int compareTo(Address addr) {
		if (this.getName().equalsIgnoreCase(addr.getName())) {
			if (this.getCity().equalsIgnoreCase(addr.getCity())) {
				if (this.getStateCode().equalsIgnoreCase(addr.getStateCode())) {
					return this.getAddress1().compareToIgnoreCase(addr.getAddress1());
				} else {
					return this.getStateCode().compareToIgnoreCase(addr.getStateCode());
				}
			} else {
				return this.getCity().compareToIgnoreCase(addr.getCity());
			}
		} else {
			return this.getName().compareToIgnoreCase(addr.getName());
		}
	}
	
	/**
	* An address is equal to another address if the Name, Address1, City and StateCode match between them
	*/
	@Override
	public boolean equals(Object obj) {
		if ( ! (obj instanceof Address) ) {
			return false;
		}
		Address addr = (Address)obj;
		if (ReceiptPrinterStringUtils.isNullOrEmpty(this.getName()) || ReceiptPrinterStringUtils.isNullOrEmpty(addr.getName()) ||
			ReceiptPrinterStringUtils.isNullOrEmpty(this.getAddress1()) || ReceiptPrinterStringUtils.isNullOrEmpty(addr.getAddress1()) ||
			ReceiptPrinterStringUtils.isNullOrEmpty(this.getCity()) || ReceiptPrinterStringUtils.isNullOrEmpty(addr.getCity()) ||
			ReceiptPrinterStringUtils.isNullOrEmpty(this.getStateCode()) || ReceiptPrinterStringUtils.isNullOrEmpty(addr.getStateCode())) {
			return false;
		}
		if (this.getName().trim().equalsIgnoreCase(addr.getName().trim()) &&
			this.getAddress1().trim().equalsIgnoreCase(addr.getAddress1().trim()) &&
			this.getCity().trim().equalsIgnoreCase(addr.getCity().trim()) &&
			this.getStateCode().trim().equalsIgnoreCase(addr.getStateCode().trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	* An address is equal to another address if the Name, Address1, City and StateCode match between them
	*/
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + name.hashCode();
		result = 31 * result + address1.hashCode();
		result = 31 * result + city.hashCode();
		result = 31 * result + stateCode.hashCode();
		return result;
	}

}
