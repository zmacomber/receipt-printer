package com.mbc.receiptprinter.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.mbc.receiptprinter.util.ReceiptPrinterDateUtils;
import com.mbc.receiptprinter.util.ReceiptPrinterNumberUtils;

/**
* Bean for storing receipts
*/
public class Receipt implements Comparable<Receipt>{
	
	private final String receiptDate;
	private final Address address;
	private final Designation designation;
	private final String amount;
	private final String notes;
	
	private Receipt(String receiptDate, Address address, Designation designation, String amount, String notes) {
		this.receiptDate = receiptDate;
		this.address = address;
		this.designation = designation;
		this.amount = amount;
		this.notes = notes;
	}
	
	public static Receipt newInstance(String receiptDate, Address address, Designation designation, String amount, String notes) {
		return new Receipt(receiptDate, address, designation, amount, notes);
	}
	
	public String getReceiptDate() 		{ return receiptDate; 	}
	public Address getAddress() 		{ return address; 	}
	public Designation getDesignation()	{ return designation; 	}
	public String getAmount() 		{ return amount; 	}
	public String getNotes() 		{ return notes;		}

	@Override
	public String toString() {
		return "Receipt [receiptDate=" + receiptDate + ", address="
				+ address + ", designation=" + designation + ", amount="
				+ amount + ", notes=" + notes + "]";
	}

	/**
	* Comparing is done based on this order: receipt date (latest date wins in comparison), address, designation,
	* amount (lesser amount wins in comparison)
	*/	
	@Override
	public int compareTo(Receipt receipt) {
		if (this.getReceiptDate().equalsIgnoreCase(receipt.getReceiptDate())) {
			if (this.getAddress().equals(receipt.getAddress())) {
				if (this.getDesignation().equals(receipt.getDesignation())) {
					// Compare as decimals
					BigDecimal bd1 = ReceiptPrinterNumberUtils.getBigDecimal(this.getAmount());
					BigDecimal bd2 = ReceiptPrinterNumberUtils.getBigDecimal(receipt.getAmount());
					return bd1.compareTo(bd2);
				} else {
					return this.getDesignation().compareTo(receipt.getDesignation());
				}
			} else {
				return this.getAddress().compareTo(receipt.getAddress());
			}
		} else {
			// Compare as dates - sort descending
			Date d1 = ReceiptPrinterDateUtils.getDate(this.getReceiptDate());
			Date d2 = ReceiptPrinterDateUtils.getDate(receipt.getReceiptDate());
			return d2.compareTo(d1);
		}
	}
}
