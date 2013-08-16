package com.mbc.receiptprinter.ui.print;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JSeparator;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.bean.Designation;
import com.mbc.receiptprinter.bean.Receipt;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;
import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

import java.awt.Dimension;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;

/**
 * Gets invoked when the user wants to print a Receipt.
 * Displays a preview of what the Receipt will look like when printed.
 * This JFrame will display alongside a print dialog to print the Receipt.
 */
public class ReceiptPrintOut extends JFrame implements Printable {

	private static final long serialVersionUID = 1L;

	public ReceiptPrintOut(Receipt receipt, Address address, Designation designation) {		
		setupOverallStructure();
		setupMidcoastAddress();
		setupTopSeparator();
		setupReceiptDate(receipt);
		setupReceivedFromLabel();
		setupReceivedFromAddress(address);
		setupReceiptAmount(receipt);
		setupDesignation(designation);
		setupReceiptNotes(receipt);
		setupThankYouMessage();
		setupBottomSeparator();
		setupVerse();
	}

	void setupOverallStructure() {
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		getContentPane().setLayout(null);
	}

	void setupMidcoastAddress() {
		JTextPane txtpnMidcoastBaptistChurch = new JTextPane();
		txtpnMidcoastBaptistChurch.setBounds(36, 0, 193, 45);
		txtpnMidcoastBaptistChurch.setBackground(Color.WHITE);
		txtpnMidcoastBaptistChurch.setForeground(Color.BLACK);
		txtpnMidcoastBaptistChurch.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtpnMidcoastBaptistChurch.setEditable(false);
		txtpnMidcoastBaptistChurch.setText("Mid-Coast Baptist Church\r\nP.O. Box 537\r\nBrunswick, ME 04011-0537");
		getContentPane().add(txtpnMidcoastBaptistChurch);
	}

	void setupTopSeparator() {
		JSeparator separator = new JSeparator();
		separator.setSize(new Dimension(0, 10));
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(30, 71, 540, 2);
		getContentPane().add(separator);
	}

	void setupReceiptDate(Receipt receipt) {
		JLabel label = new JLabel(receipt.getReceiptDate());
		label.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label.setBounds(444, 25, 79, 20);
		getContentPane().add(label);
	}

	void setupReceivedFromLabel() {
		JLabel lblReceivedFrom = new JLabel("Received From:");
		lblReceivedFrom.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblReceivedFrom.setBounds(37, 84, 132, 20);
		getContentPane().add(lblReceivedFrom);
	}

	void setupReceivedFromAddress(Address address) {
		JTextPane textPane = new JTextPane();
		StringBuilder addressText = new StringBuilder();
		addressText.append(address.getName());
		addressText.append("\r\n");
		addressText.append(address.getAddress1());
		addressText.append("\r\n");
		if (ReceiptPrinterStringUtils.isNotNullOrEmpty(address.getAddress2())) {
			addressText.append(address.getAddress2());
			addressText.append("\r\n");
		}
		addressText.append(address.getCity());
		addressText.append(", ");
		addressText.append(address.getStateCode());
		addressText.append(" ");
		addressText.append(address.getZipCode());
		textPane.setText(addressText.toString());
		textPane.setForeground(new Color(0, 0, 0));
		textPane.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textPane.setEditable(false);
		textPane.setBackground(Color.WHITE);
		textPane.setBounds(67, 122, 221, 65);
		getContentPane().add(textPane);
	}

	void setupReceiptAmount(Receipt receipt) {
		JLabel lblAmount = new JLabel("Amount:      $      " + receipt.getAmount());
		lblAmount.setFont(new Font("Serif", Font.PLAIN, 12));
		lblAmount.setBounds(366, 111, 157, 40);
		getContentPane().add(lblAmount);
	}

	void setupDesignation(Designation designation) {
		JTextPane txtpnDesignationBobMitchell = new JTextPane();
		txtpnDesignationBobMitchell.setText("Designation:       " + designation.getName());
		txtpnDesignationBobMitchell.setForeground(Color.BLACK);
		txtpnDesignationBobMitchell.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtpnDesignationBobMitchell.setEditable(false);
		txtpnDesignationBobMitchell.setBackground(Color.WHITE);
		txtpnDesignationBobMitchell.setBounds(67, 188, 221, 20);
		getContentPane().add(txtpnDesignationBobMitchell);
	}

	void setupReceiptNotes(Receipt receipt) {
		JTextPane txtpnThisIs = new JTextPane();
		txtpnThisIs.setEditable(false);
		txtpnThisIs.setForeground(Color.BLACK);
		txtpnThisIs.setBackground(Color.WHITE);
		txtpnThisIs.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtpnThisIs.setText(receipt.getNotes());
		txtpnThisIs.setBounds(150, 210, 284, 55);
		getContentPane().add(txtpnThisIs);
	}

	void setupThankYouMessage() {
		JLabel lblNewLabel = new JLabel("Thank you for your investment in church planting.");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(137, 265, 318, 40);
		getContentPane().add(lblNewLabel);
	}

	void setupBottomSeparator() {
		JSeparator separator_1 = new JSeparator();
		separator_1.setSize(new Dimension(0, 10));
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(30, 305, 540, 13);
		getContentPane().add(separator_1);
	}

	void setupVerse() {
		JLabel lblJesusSaidi = new JLabel(ReceiptPrinterProperties.getProperty("receipt.printout.verse"));
		lblJesusSaidi.setForeground(new Color(0, 0, 0));
		String verseFontName = ReceiptPrinterProperties.getProperty("receipt.printout.verse.font");
		int verseFontSize = Integer.valueOf(ReceiptPrinterProperties.getProperty("receipt.printout.verse.font.size"));
		lblJesusSaidi.setFont(new Font(verseFontName, Font.PLAIN, verseFontSize));
		lblJesusSaidi.setBounds(72, 305, 467, 40);
		getContentPane().add(lblJesusSaidi);
	}

	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

		if (page > 0) { /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}

		/*
		 * User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping
		 */
		int xOffset = Integer.valueOf(ReceiptPrinterProperties.getProperty("receipt.printout.x.offset"));
		int yOffset = Integer.valueOf(ReceiptPrinterProperties.getProperty("receipt.printout.y.offset"));
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX() + xOffset, pf.getImageableY() + yOffset);

		/* Now print the window and its visible contents */
		this.print(g);

		/* tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;
	}

	public void printReceipt() {
		PrinterJob job = PrinterJob.getPrinterJob();
		Paper paper = new Paper();
		paper.setImageableArea(0, 0, Integer.valueOf(ReceiptPrinterProperties.getProperty("receipt.printout.area.width")), 
									 Integer.valueOf(ReceiptPrinterProperties.getProperty("receipt.printout.area.height")));
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.PORTRAIT);
		pf.setPaper(paper);
		
		Book book = new Book();
		book.append(this, pf);
		job.setPageable(book);
		
		boolean ok = job.printDialog();
		if (ok) {
			try {
				job.print();
			} catch (PrinterException ex) {
				ReceiptPrinterLogger.logMessage(getClass(), Level.WARNING, "Exception while trying to print receipt", ex);
			}
		}
	}
}
