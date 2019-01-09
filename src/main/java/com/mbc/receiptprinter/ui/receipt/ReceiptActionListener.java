package com.mbc.receiptprinter.ui.receipt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.constant.ActionCommand;
import com.mbc.receiptprinter.process.address.AddressFetchProcess;
import com.mbc.receiptprinter.process.designation.DesignationFetchProcess;
import com.mbc.receiptprinter.process.receipt.ReceiptAppendProcess;
import com.mbc.receiptprinter.ui.tabs.ReceiptTab;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;
import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

/**
 * Receipt action class that gets invoked when a receipt is added
 */
public class ReceiptActionListener implements ActionListener {
	
	private ReceiptTab receiptTab;
	public ReceiptActionListener(ReceiptTab receiptTab) { this.receiptTab = receiptTab; }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ActionCommand.ADD_RECEIPT)) {
			AddressFetchProcess afp = new AddressFetchProcess();
			Address addr = afp.fetchAddressFromReceipt(receiptTab.getAddress().getSelectedItem().toString());
			Designation designation = extractDesignationFromSelectedItem(receiptTab.getDesignation().getSelectedItem().toString());
			Receipt receipt = Receipt.newInstance(receiptTab.getReceiptDate().getText(), 
							      				  addr, 
							      				  designation, 
							      				  receiptTab.getAmount().getText(), 
							      				  receiptTab.getNotes().getText());
			
			ReceiptAppendProcess receiptAppend = new ReceiptAppendProcess();
			String outcome = receiptAppend.appendReceipt(receipt); 
								
			JOptionPane.showMessageDialog(ReceiptPrinterUIUtils.getMainFrame(), outcome);
			
			if (outcome.equals(ReceiptPrinterProperties.getProperty("receipt.outcome.added"))) {
				receiptTab.getReceiptTable().repopulate();
			}
		}
	}
	
	Designation extractDesignationFromSelectedItem(String designationComboBoxSelectedItem) {
		DesignationFetchProcess dfp = new DesignationFetchProcess();
		return dfp.fetchDesignation(designationComboBoxSelectedItem);
	}
}
