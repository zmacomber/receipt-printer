package com.mbc.receiptprinter.ui.address;

import javax.swing.JOptionPane;

import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

public class AddressTableDeleteOptionPane extends JOptionPane {

	private static final long serialVersionUID = 1L;
	
	public static Object[] options = {"Yes", "No"};
	
	public static int showOptionDialog(String addressName) {
		return showOptionDialog(ReceiptPrinterUIUtils.getMainFrame(), 
	 			  				"Do you want to delete address " + addressName + "?", 
	 			  				"Delete Address", 
	 			  				YES_NO_OPTION, 
	 			  				QUESTION_MESSAGE, 
	 			  				null, 
	 			  				options, 
	 			  				options[0]);
	}
}
