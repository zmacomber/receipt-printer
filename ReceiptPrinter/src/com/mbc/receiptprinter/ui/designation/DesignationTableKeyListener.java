package com.mbc.receiptprinter.ui.designation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import com.mbc.receiptprinter.process.designation.DesignationDeleteProcess;
import com.mbc.receiptprinter.process.designation.DesignationFetchProcess;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;
import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

public class DesignationTableKeyListener implements KeyListener {
	
	private DesignationTable table;
	public DesignationTableKeyListener(DesignationTable table) { this.table = table; }

	@Override
	public void keyPressed(KeyEvent e) {
		if (KeyEvent.getKeyText(e.getKeyCode()).toUpperCase().equals("DELETE")) {
			if (table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(ReceiptPrinterUIUtils.getMainFrame(), "Please select a designation to delete");
			} else {
				String name = table.getModel().getValueAt(table.getSelectedRow(), 0).toString().trim();
				int selectedOption = DesignationTableDeleteOptionPane.showOptionDialog(name);
				if (selectedOption == 0) {
					DesignationDeleteProcess designationDeleteProcess = new DesignationDeleteProcess();
					DesignationFetchProcess designationFetchProcess = new DesignationFetchProcess();
					String outcome = designationDeleteProcess.deleteDesignation(designationFetchProcess.fetchDesignation(name));
					if (outcome.equals(ReceiptPrinterProperties.getProperty("designation.outcome.deleted"))) {
						table.repopulate();
					} else {
						JOptionPane.showMessageDialog(ReceiptPrinterUIUtils.getMainFrame(), outcome);
					}
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) { }
}
