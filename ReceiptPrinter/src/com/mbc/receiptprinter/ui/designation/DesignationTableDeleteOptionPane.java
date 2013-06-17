package com.mbc.receiptprinter.ui.designation;

import javax.swing.JOptionPane;

import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

public class DesignationTableDeleteOptionPane extends JOptionPane {

	private static final long serialVersionUID = 1L;
	
	public static Object[] options = {"Yes", "No"};
	
	public static int showOptionDialog(String designationName) {
		return showOptionDialog(ReceiptPrinterUIUtils.getMainFrame(), 
	 			  				"Do you want to delete designation " + designationName + "?", 
	 			  				"Delete Designation", 
	 			  				YES_NO_OPTION, 
	 			  				QUESTION_MESSAGE, 
	 			  				null, 
	 			  				options, 
	 			  				options[0]);
	}
}
