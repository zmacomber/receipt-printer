package com.mbc.receiptprinter.ui.tabs;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mbc.receiptprinter.ui.ReceiptPrinterLabel;
import com.mbc.receiptprinter.ui.ReceiptPrinterTextField;
import com.mbc.receiptprinter.ui.constraints.*;
import com.mbc.receiptprinter.ui.designation.DesignationAddButton;
import com.mbc.receiptprinter.ui.designation.DesignationGridBagLayout;
import com.mbc.receiptprinter.ui.designation.DesignationTable;
import com.mbc.receiptprinter.ui.designation.DesignationTableScrollPane;

public class DesignationTab extends JPanel {	
	private static final long serialVersionUID = 1L;
	private DesignationTable designationTable;
	private JTextField txtName;

	public DesignationTab() {
		setLayout(new DesignationGridBagLayout());
		
		designationTable  = new DesignationTable();
		txtName           = new ReceiptPrinterTextField();
		
		add(new ReceiptPrinterLabel("Name:"), new ReceiptPrinterConstraints.Builder().gridx(2).gridy(1).anchor(GridBagConstraints.EAST).build());
		add(txtName, new ReceiptPrinterConstraints.Builder().gridx(3).gridy(1).fill(GridBagConstraints.BOTH).anchor(GridBagConstraints.LINE_START).build());
		
		add(new DesignationAddButton(this), new ReceiptPrinterConstraints.Builder().gridx(3).gridy(2).anchor(GridBagConstraints.WEST).build());	
		add(new DesignationTableScrollPane(designationTable), new ReceiptPrinterConstraints.Builder().gridx(1).gridy(3).fill(GridBagConstraints.BOTH).gridwidth(4).build());
	}

	public DesignationTable getDesignationTable() 	{ return designationTable;  }
	public JTextField getTxtName() 					{ return txtName; 			}
}
