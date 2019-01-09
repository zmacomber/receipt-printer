package com.mbc.receiptprinter.util;

import java.util.List;

public interface ReceiptPrinterNameUtils<T> {
	
	public boolean nameAlreadyExists(String name, List<T> list);

}
