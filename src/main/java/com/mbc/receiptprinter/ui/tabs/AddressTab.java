package com.mbc.receiptprinter.ui.tabs;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mbc.receiptprinter.constant.StateCode;
import com.mbc.receiptprinter.ui.ReceiptPrinterComboBox;
import com.mbc.receiptprinter.ui.ReceiptPrinterLabel;
import com.mbc.receiptprinter.ui.ReceiptPrinterTextField;
import com.mbc.receiptprinter.ui.address.*;
import com.mbc.receiptprinter.ui.constraints.*;

public class AddressTab extends JPanel {	
	private static final long serialVersionUID = 1L;
	private AddressTable addressTable;
	private JTextField txtName;
	private JTextField txtAddress1;
	private JTextField txtAddress2;
	private JTextField txtCity;
	private JTextField txtZip;
	private JComboBox<String> comboBoxState;

	public AddressTab() {
		setLayout(new AddressGridBagLayout());
		
		add(new ReceiptPrinterLabel("Name:"), new ReceiptPrinterConstraints.Builder().gridx(2).gridy(1).anchor(GridBagConstraints.EAST).build());
		add(new ReceiptPrinterLabel("Address 1:"), new ReceiptPrinterConstraints.Builder().gridx(2).gridy(2).anchor(GridBagConstraints.EAST).build());
		add(new ReceiptPrinterLabel("Address 2:"), new ReceiptPrinterConstraints.Builder().gridx(2).gridy(3).anchor(GridBagConstraints.EAST).build());
		add(new ReceiptPrinterLabel("City:"), new ReceiptPrinterConstraints.Builder().gridx(2).gridy(4).anchor(GridBagConstraints.EAST).build());
		add(new ReceiptPrinterLabel("State:"), new ReceiptPrinterConstraints.Builder().gridx(2).gridy(5).anchor(GridBagConstraints.EAST).build());
		add(new ReceiptPrinterLabel("Zip:"), new ReceiptPrinterConstraints.Builder().gridx(2).gridy(6).anchor(GridBagConstraints.EAST).build());
		
		addressTable  = new AddressTable();
		txtName       = new ReceiptPrinterTextField(); 	
		txtAddress1   = new ReceiptPrinterTextField();
		txtAddress2   = new ReceiptPrinterTextField(); 
		txtCity       = new ReceiptPrinterTextField();
		comboBoxState = new ReceiptPrinterComboBox("STATE_COMBO", StateCode.getNames());
		txtZip        = new ReceiptPrinterTextField();
		
		add(txtName, new ReceiptPrinterConstraints.Builder().gridx(3).gridy(1).fill(GridBagConstraints.BOTH).anchor(GridBagConstraints.LINE_START).build());
		add(txtAddress1, new ReceiptPrinterConstraints.Builder().gridx(3).gridy(2).fill(GridBagConstraints.BOTH).anchor(GridBagConstraints.LINE_START).build());
		add(txtAddress2, new ReceiptPrinterConstraints.Builder().gridx(3).gridy(3).fill(GridBagConstraints.BOTH).anchor(GridBagConstraints.LINE_START).build());
		add(txtCity, new ReceiptPrinterConstraints.Builder().gridx(3).gridy(4).fill(GridBagConstraints.BOTH).anchor(GridBagConstraints.LINE_START).build());
		add(comboBoxState, new ReceiptPrinterConstraints.Builder().gridx(3).gridy(5).fill(GridBagConstraints.BOTH).anchor(GridBagConstraints.LINE_START).build());
		add(txtZip, new ReceiptPrinterConstraints.Builder().gridx(3).gridy(6).fill(GridBagConstraints.BOTH).anchor(GridBagConstraints.LINE_START).build());
		
		add(new AddressAddButton(this), new ReceiptPrinterConstraints.Builder().gridx(3).gridy(7).anchor(GridBagConstraints.WEST).build());
		add(new AddressPrintButton(this), new ReceiptPrinterConstraints.Builder().gridx(4).gridy(7).anchor(GridBagConstraints.WEST).build());
		add(new AddressTableScrollPane(addressTable), new ReceiptPrinterConstraints.Builder().gridx(1).gridy(8).fill(GridBagConstraints.BOTH).gridwidth(4).build());
	}

	public AddressTable getAddressTable() 		{ return addressTable;  }
	public JTextField getTxtName() 				{ return txtName; 		}
	public JTextField getTxtAddress1() 			{ return txtAddress1; 	}
	public JTextField getTxtAddress2() 			{ return txtAddress2;	}
	public JTextField getTxtCity() 				{ return txtCity;		}
	public JTextField getTxtZip() 				{ return txtZip;		}
	public JComboBox<String> getComboBoxState() { return comboBoxState;	}
}
