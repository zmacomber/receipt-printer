package com.mbc.receiptprinter.ui.designation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.constant.ActionCommand;
import com.mbc.receiptprinter.process.designation.DesignationAppendProcess;
import com.mbc.receiptprinter.ui.tabs.DesignationTab;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;
import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

/**
 * Designation action class that gets invoked when a Designation is added
 */
public class DesignationActionListener implements ActionListener {
	
	private DesignationTab designationTab;
	
	public DesignationActionListener(DesignationTab designationTab) { this.designationTab = designationTab; }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ActionCommand.ADD_DESIGNATION)) {
			Designation designation = Designation.newInstance(designationTab.getTxtName().getText());
			
			DesignationAppendProcess designationAppendProcess = new DesignationAppendProcess();
			String outcome = designationAppendProcess.appendDesignation(designation); 
								
			JOptionPane.showMessageDialog(ReceiptPrinterUIUtils.getMainFrame(), outcome);
			
			if (outcome.equals(ReceiptPrinterProperties.getProperty("designation.outcome.added"))) {
				designationTab.getDesignationTable().repopulate();
			}
		}
	}
}
