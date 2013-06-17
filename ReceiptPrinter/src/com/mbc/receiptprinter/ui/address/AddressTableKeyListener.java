package com.mbc.receiptprinter.ui.address;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import com.mbc.receiptprinter.process.address.AddressDeleteProcess;
import com.mbc.receiptprinter.process.address.AddressFetchProcess;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;
import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

public class AddressTableKeyListener implements KeyListener {
	
	private AddressTable table;
	public AddressTableKeyListener(AddressTable table) { this.table = table; }

	@Override
	public void keyPressed(KeyEvent e) {
		if (KeyEvent.getKeyText(e.getKeyCode()).toUpperCase().equals("DELETE")) {
			if (table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(ReceiptPrinterUIUtils.getMainFrame(), "Please select an address to delete");
			} else {
				String name = table.getModel().getValueAt(table.getSelectedRow(), 0).toString().trim();
				String address1 = table.getModel().getValueAt(table.getSelectedRow(), 1).toString().trim();
				String city = table.getModel().getValueAt(table.getSelectedRow(), 3).toString().trim();
				String stateCode = table.getModel().getValueAt(table.getSelectedRow(), 4).toString().trim();
				
				int selectedOption = AddressTableDeleteOptionPane.showOptionDialog(name);
				
				if (selectedOption == 0) {
					AddressDeleteProcess deleteAddressProcess = new AddressDeleteProcess();
					AddressFetchProcess fetchAddressProcess = new AddressFetchProcess();
					String outcome = deleteAddressProcess.deleteAddress(fetchAddressProcess.fetchAddress(name, address1, city, stateCode));
					if (outcome.equals(ReceiptPrinterProperties.getProperty("address.outcome.deleted"))) {
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
