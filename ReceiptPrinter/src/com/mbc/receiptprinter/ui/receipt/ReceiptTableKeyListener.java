package com.mbc.receiptprinter.ui.receipt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.process.address.AddressFetchProcess;
import com.mbc.receiptprinter.process.designation.DesignationFetchProcess;
import com.mbc.receiptprinter.process.receipt.ReceiptDeleteProcess;
import com.mbc.receiptprinter.process.receipt.ReceiptFetchProcess;
import com.mbc.receiptprinter.ui.print.ReceiptPrintOut;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;
import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

public class ReceiptTableKeyListener implements KeyListener {
	
	private ReceiptTable table;
	public ReceiptTableKeyListener(ReceiptTable table) { this.table = table; }

	@Override
	public void keyPressed(KeyEvent e) {
		if (KeyEvent.getKeyText(e.getKeyCode()).toUpperCase().equals("DELETE")) {
			if (table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(ReceiptPrinterUIUtils.getMainFrame(), "Please select a receipt to delete");
			} else {
				String receiptDate     = table.getModel().getValueAt(table.getSelectedRow(), 0).toString().trim();
				String receiptAddress  = table.getModel().getValueAt(table.getSelectedRow(), 1).toString().trim();
				String designationName = table.getModel().getValueAt(table.getSelectedRow(), 2).toString().trim();
				String amount 	       = table.getModel().getValueAt(table.getSelectedRow(), 3).toString().trim();
				
				int selectedOption = ReceiptTableDeleteOptionPane.showOptionDialog(receiptDate, receiptAddress, designationName, amount);
				if (selectedOption == 0) {
					ReceiptDeleteProcess deleteProcess = new ReceiptDeleteProcess();
					ReceiptFetchProcess fetchProcess = new ReceiptFetchProcess();
					AddressFetchProcess addressFetch  = new AddressFetchProcess();
					DesignationFetchProcess designationFetch = new DesignationFetchProcess();
					
					Address address 		= addressFetch.fetchAddressFromReceipt(receiptAddress);
					Designation designation = designationFetch.fetchDesignation(designationName);
					
					String outcome = deleteProcess.deleteReceipt(fetchProcess.fetchReceipt(receiptDate, address, designation, amount));
					if (outcome.equals(ReceiptPrinterProperties.getProperty("receipt.outcome.deleted"))) {
						table.repopulate();
					} else {
						JOptionPane.showMessageDialog(ReceiptPrinterUIUtils.getMainFrame(), outcome);
					}
				}
			}
		}
		if (KeyEvent.getKeyText(e.getKeyCode()).toUpperCase().equals("P")) {
			if (table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(ReceiptPrinterUIUtils.getMainFrame(), "Please select a receipt to print");
			} else {
				String receiptDate 	   = table.getModel().getValueAt(table.getSelectedRow(), 0).toString().trim();
				String receiptAddress  = table.getModel().getValueAt(table.getSelectedRow(), 1).toString().trim();
				String designationName = table.getModel().getValueAt(table.getSelectedRow(), 2).toString().trim();
				String amount 	   	   = table.getModel().getValueAt(table.getSelectedRow(), 3).toString().trim();
				
				ReceiptFetchProcess receiptFetch 		 = new ReceiptFetchProcess();
				AddressFetchProcess addressFetch 		 = new AddressFetchProcess();
				DesignationFetchProcess designationFetch = new DesignationFetchProcess();
				
				Address address 		= addressFetch.fetchAddressFromReceipt(receiptAddress);
				Designation designation = designationFetch.fetchDesignation(designationName);
				Receipt receipt 		= receiptFetch.fetchReceipt(receiptDate, address, designation, amount);
				
				ReceiptPrintOut printOut = new ReceiptPrintOut(receipt, address, designation);
				
				printOut.setTitle(ReceiptPrinterProperties.getProperty("receipt.printout.title"));
				printOut.pack();
		        	printOut.setVisible(true);
				int xLocation = Integer.valueOf(ReceiptPrinterProperties.getProperty("receipt.printout.x_location"));
				int yLocation = Integer.valueOf(ReceiptPrinterProperties.getProperty("receipt.printout.y_location"));
				int width = Integer.valueOf(ReceiptPrinterProperties.getProperty("receipt.printout.width"));
				int height = Integer.valueOf(ReceiptPrinterProperties.getProperty("receipt.printout.height"));
			        printOut.setLocation(xLocation, yLocation);
			        printOut.setSize(width, height);
				
				printOut.printReceipt();
				printOut.dispose();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) { }
}
