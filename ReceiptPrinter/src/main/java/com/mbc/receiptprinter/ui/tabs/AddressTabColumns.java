package com.mbc.receiptprinter.ui.tabs;

import java.util.ArrayList;
import java.util.List;

public enum AddressTabColumns {
	NAME("Name", 0),
	ADDRESS1("Address 1", 1), 
	ADDRESS2("Address 2", 2), 
	CITY("City", 3), 
	STATE("State", 4), 
	ZIP("Zip Code", 5);
	
	private String name;
	private int column;
	
	private AddressTabColumns(String name, int column) {
		this.name = name;
		this.column = column;
	}
	
	public String getName() 			{ return name; }
	public int getColumn() 				{ return column; }
	
	public void setName(String name)	{ this.name = name; }
	public void setColumn(int column)	{ this.column = column; }
	
	public static Object[] getAllNames() {
		List<String> names = new ArrayList<String>();
		for (AddressTabColumns cn : AddressTabColumns.values()) {
			names.add(cn.getName());
		}
		return names.toArray();
	}
}