package com.mbc.receiptprinter.constant;

/**
* File Delimiters for the use of separating fields and records stored in the data files.
* FIELD(ASCII code 31) & RECORD(ASCII code 30) are specific ASCII non-printing characters.
* It's important to modify cautiously any data files containing these characters as they
* won't be seen except in text editors that have the ability to display non-printing characters.
*/
public class FileDelimiters {

	public static final String FIELD  = String.valueOf((char)31);
	public static final String RECORD = String.valueOf((char)30);
	
}
