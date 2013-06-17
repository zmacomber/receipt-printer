package com.mbc.receiptprinter.ui.tabs;

import java.util.ArrayList;
import java.util.List;

public enum TotalYearlyAmountReportTabColumns {
	NAME("Name", 0), 
	AMOUNT("Amount", 1);
	
	private String name;
	private int column;
	
	private TotalYearlyAmountReportTabColumns(String name, int column) {
		this.name = name;
		this.column = column;
	}
	
	public String getName() 			{ return name; }
	public int getColumn() 				{ return column; }
	
	public void setName(String name)	{ this.name = name; }
	public void setColumn(int column)	{ this.column = column; }
	
	public static Object[] getAllNames() {
		List<String> names = new ArrayList<String>();
		for (TotalYearlyAmountReportTabColumns cn : TotalYearlyAmountReportTabColumns.values()) {
			names.add(cn.getName());
		}
		return names.toArray();
	}
}