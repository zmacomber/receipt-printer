package com.mbc.receiptprinter.ui.address;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.constant.ActionCommand;
import com.mbc.receiptprinter.constant.StateCode;
import com.mbc.receiptprinter.process.address.AddressAppendProcess;
import com.mbc.receiptprinter.process.address.AddressFetchProcess;
import com.mbc.receiptprinter.ui.tabs.AddressTab;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;
import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

/**
 * Address action class that gets invoked when an address is added or addresses are printed
 */
public class AddressActionListener implements ActionListener {
	
	private AddressTab addressTab;
	
	public AddressActionListener(AddressTab addressTab) { this.addressTab = addressTab; }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Action for the adding of an Address
		if (e.getActionCommand().equals(ActionCommand.ADD_ADDRESS)) {
			AddressFetchProcess afp = new AddressFetchProcess();
			Address addr = Address.newInstance(afp.getLastAddressId() + 1,
											   addressTab.getTxtName().getText(),
											   addressTab.getTxtAddress1().getText(),
											   addressTab.getTxtAddress2().getText(),
											   addressTab.getTxtCity().getText(),
					   						   StateCode.getCodeByName(addressTab.getComboBoxState().getSelectedItem().toString()),
					   						   addressTab.getTxtZip().getText());
			
			AddressAppendProcess appendAddressProcess = new AddressAppendProcess();
			String outcome = appendAddressProcess.appendAddress(addr); 
								
			JOptionPane.showMessageDialog(ReceiptPrinterUIUtils.getMainFrame(), outcome);
			
			// Reset the address form fields if the address was added successfully
			if (outcome.equals(ReceiptPrinterProperties.getProperty("address.outcome.added"))) {
				addressTab.getAddressTable().repopulate();
				addressTab.getTxtName().setText("");
				addressTab.getTxtAddress1().setText("");
				addressTab.getTxtAddress2().setText("");
				addressTab.getTxtCity().setText("");
				addressTab.getTxtZip().setText("");
			}
		}
		
		// Action for the printing of addresses
		if (e.getActionCommand().equals(ActionCommand.PRINT_ADDRESSES)) {	
			addressTab.getAddressTable().printAddresses();
		}
	}
}
