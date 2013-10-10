package com.mbc.receiptprinter.ui.receipt;

import javax.swing.JOptionPane;

import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

public class ReceiptTableDeleteOptionPane extends JOptionPane {

	private static final long serialVersionUID = 1L;
	
	public static Object[] options = {"Yes", "No"};
	
	public static int showOptionDialog(String receiptDate, String addressName, String designation, String amount) {
		return showOptionDialog(ReceiptPrinterUIUtils.getMainFrame(), 
	 			  				"Do you want to delete receipt with date '" + receiptDate + "', " +
	 			  				"address name '" + addressName + "', " +
	 			  				"designation '" + designation + "' and " +
	 			  				"amount '" + amount + "'?",
	 			  				"Delete Receipt", 
	 			  				YES_NO_OPTION, 
	 			  				QUESTION_MESSAGE, 
	 			  				null, 
	 			  				options, 
	 			  				options[0]);
	}
}
