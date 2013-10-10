package com.mbc.receiptprinter.ui.tabs;

import java.util.ArrayList;
import java.util.List;

public enum ReceiptTabColumns {
	RECEIPT_DATE("Date", 0),
	ADDRESS("Address", 1), 
	DESIGNATION("Designation", 2), 
	AMOUNT("Amount", 3), 
	NOTES("Notes", 4);
	
	private String name;
	private int column;
	
	private ReceiptTabColumns(String name, int column) {
		this.name = name;
		this.column = column;
	}
	
	public String getName() 			{ return name; }
	public int getColumn() 				{ return column; }
	
	public void setName(String name)	{ this.name = name; }
	public void setColumn(int column)	{ this.column = column; }
	
	public static Object[] getAllNames() {
		List<String> names = new ArrayList<String>();
		for (ReceiptTabColumns cn : ReceiptTabColumns.values()) {
			names.add(cn.getName());
		}
		return names.toArray();
	}
}