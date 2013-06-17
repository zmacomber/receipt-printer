package com.mbc.receiptprinter.ui.designation;

import java.awt.Font;

import javax.swing.JButton;

import com.mbc.receiptprinter.constant.ActionCommand;
import com.mbc.receiptprinter.ui.tabs.DesignationTab;

public class DesignationAddButton extends JButton {

	private static final long serialVersionUID = 1L;

	public DesignationAddButton(DesignationTab designationTab) {
		setText("Add Designation");
		setActionCommand(ActionCommand.ADD_DESIGNATION);
		addActionListener(new DesignationActionListener(designationTab));
		setFont(new Font("Tahoma", Font.PLAIN, 12));
	}
}
