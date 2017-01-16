package com.mbc.receiptprinter.bean;

import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

/**
* Bean for storing designations
*/
public class Designation implements Comparable<Designation> {

	private final String name;
	
	private Designation(String name) {
		this.name = name;
	}
	
	public static Designation newInstance(String name) {
		return new Designation(name);
	}
	
	public String getName() { 
		return name; 
	}
	
	@Override
	public String toString() { 
		return "Designation [name=" + name + "]"; 
	}

	/**
	* Comparing is done based off of the designation name
	*/
	@Override
	public int compareTo(Designation designation) {
		return this.getName().compareToIgnoreCase(designation.getName());
	}
	
	/**
	* A designation is equal to another designation if the Name match between them
	*/
	@Override
	public boolean equals(Object obj) {
		if ( ! (obj instanceof Designation) ) {
			return false;
		}
		Designation designation = (Designation)obj;
		if (ReceiptPrinterStringUtils.isNullOrEmpty(this.getName()) || ReceiptPrinterStringUtils.isNullOrEmpty(designation.getName())) {
			return false;
		}
		if (this.getName().trim().equalsIgnoreCase(designation.getName().trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	* A designation is equal to another designation if the Name match between them
	*/
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + name.hashCode();
		return result;
	}
}
