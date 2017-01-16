package com.mbc.receiptprinter.ui.tabs;

import java.awt.GridBagConstraints;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mbc.receiptprinter.process.address.AddressFetchProcess;
import com.mbc.receiptprinter.process.designation.DesignationFetchProcess;
import com.mbc.receiptprinter.ui.ReceiptPrinterComboBox;
import com.mbc.receiptprinter.ui.ReceiptPrinterLabel;
import com.mbc.receiptprinter.ui.ReceiptPrinterTextAreaField;
import com.mbc.receiptprinter.ui.ReceiptPrinterTextField;
import com.mbc.receiptprinter.ui.constraints.*;
import com.mbc.receiptprinter.ui.receipt.ReceiptAddButton;
import com.mbc.receiptprinter.ui.receipt.ReceiptGridBagLayout;
import com.mbc.receiptprinter.ui.receipt.ReceiptNotesScrollPane;
import com.mbc.receiptprinter.ui.receipt.ReceiptTable;
import com.mbc.receiptprinter.ui.receipt.ReceiptTableScrollPane;
import com.mbc.receiptprinter.util.ReceiptPrinterDateUtils;

public class ReceiptTab extends JPanel {	
	private static final long serialVersionUID = 1L;
	
	private ReceiptTable receiptTable;
	
	private JTextField receiptDate;
	private JComboBox<String> address;
	private JComboBox<String> designation;
	private JTextField amount;
	private JTextArea notes;
	
	private AddressFetchProcess addressFetch;
	private DesignationFetchProcess designationFetch;
	
	public ReceiptTab() {
		addressFetch 	 = new AddressFetchProcess();
		designationFetch = new DesignationFetchProcess();
		
		setLayout(new ReceiptGridBagLayout());
		
		add(new ReceiptPrinterLabel("Date:"), new ReceiptPrinterConstraints.Builder().gridx(2).gridy(1).anchor(GridBagConstraints.EAST).build());
		add(new ReceiptPrinterLabel("Address:"), new ReceiptPrinterConstraints.Builder().gridx(2).gridy(2).anchor(GridBagConstraints.EAST).build());
		add(new ReceiptPrinterLabel("Designation:"), new ReceiptPrinterConstraints.Builder().gridx(2).gridy(3).anchor(GridBagConstraints.EAST).build());
		add(new ReceiptPrinterLabel("Amount:"), new ReceiptPrinterConstraints.Builder().gridx(2).gridy(4).anchor(GridBagConstraints.EAST).build());
		add(new ReceiptPrinterLabel("Notes:"), new ReceiptPrinterConstraints.Builder().gridx(2).gridy(5).anchor(GridBagConstraints.EAST).build());
				
		receiptTable  = new ReceiptTable();
		receiptDate   = new ReceiptPrinterTextField();
		receiptDate.setText(ReceiptPrinterDateUtils.getTodaysFormattedDate());
		address       = new ReceiptPrinterComboBox("ADDRESS", addressFetch.getAddressesForReceipts()); 
		designation   = new ReceiptPrinterComboBox("DESIGNATION", designationFetch.getDesignationNames()); 	 
		amount        = new ReceiptPrinterTextField(); 	
		notes 	      = new ReceiptPrinterTextAreaField(); 
		
		add(receiptDate, new ReceiptPrinterConstraints.Builder().gridx(3).gridy(1).fill(GridBagConstraints.BOTH).anchor(GridBagConstraints.LINE_START).build());
		add(address, new ReceiptPrinterConstraints.Builder().gridx(3).gridy(2).fill(GridBagConstraints.BOTH).anchor(GridBagConstraints.LINE_START).build());
		add(designation, new ReceiptPrinterConstraints.Builder().gridx(3).gridy(3).fill(GridBagConstraints.BOTH).anchor(GridBagConstraints.LINE_START).build());
		add(amount, new ReceiptPrinterConstraints.Builder().gridx(3).gridy(4).fill(GridBagConstraints.BOTH).anchor(GridBagConstraints.LINE_START).build());	
		add(new ReceiptNotesScrollPane(notes), new ReceiptPrinterConstraints.Builder().gridx(3).gridy(5).fill(GridBagConstraints.BOTH).anchor(GridBagConstraints.LINE_START).build());
		
		add(new ReceiptAddButton(this), new ReceiptPrinterConstraints.Builder().gridx(3).gridy(6).anchor(GridBagConstraints.WEST).build());		
		add(new ReceiptTableScrollPane(receiptTable), new ReceiptPrinterConstraints.Builder().gridx(1).gridy(7).fill(GridBagConstraints.BOTH).gridwidth(4).build());
	}

	public void refreshComboBoxes() {
		address.setModel(new DefaultComboBoxModel<String>(addressFetch.getAddressesForReceipts()));
		designation.setModel(new DefaultComboBoxModel<String>(designationFetch.getDesignationNames()));
		repaint();
	}
	
	public ReceiptTable getReceiptTable() 		{ return receiptTable; 	}
	public JTextField getReceiptDate() 		{ return receiptDate;	}
	public JComboBox<String> getAddress() 		{ return address;	}
	public JComboBox<String> getDesignation() 	{ return designation;	}
	public JTextField getAmount() 			{ return amount;	}
	public JTextArea getNotes() 			{ return notes;		}
}
