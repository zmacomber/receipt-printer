package com.mbc.receiptprinter.ui.print;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.ui.print.setup.*;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/**
 * Gets invoked when the user wants to print a Receipt.
 * Displays a preview of what the Receipt will look like when printed.
 * This JFrame will display alongside a print dialog to print the Receipt.
 */
public class ReceiptPrintOut extends JFrame implements Printable {

	private static final long serialVersionUID = 1L;

	private final Printer printer = new Printer(this);
	private final ReceiptPrinter receiptPrinter = new ReceiptPrinter(new ReceiptBook(this));

	public ReceiptPrintOut(Receipt receipt, Address address, Designation designation) {		
		OverallStructure.setup(this);
		getContentPane().add(new MidcoastAddress());
		getContentPane().add(new TopSeparator());
		getContentPane().add(new ReceiptDate(receipt.getReceiptDate()));
		getContentPane().add(new ReceivedFromLabel());
		getContentPane().add(new ReceivedFromAddress(address));
		getContentPane().add(new ReceiptAmount(receipt));
        getContentPane().add(new DesignationTextPane(designation));
        getContentPane().add(new ReceiptNotes(receipt));
        getContentPane().add(new ThankYouMessage());
        getContentPane().add(new BottomSeparator());
		getContentPane().add(new Verse());
	}

	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        return printer.print(g, pf, page);
	}

	public void printReceipt() {
		receiptPrinter.print();
	}
}
