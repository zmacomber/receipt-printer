package com.mbc.receiptprinter.ui.address;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.print.PrinterException;

import java.text.MessageFormat;

import java.util.logging.Level;

import com.mbc.receiptprinter.ui.print.TextTableRenderer;
import com.mbc.receiptprinter.ui.tabs.AddressTabColumns;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;

public class AddressTable extends JTable {
	
	private static final long serialVersionUID 		= 1L;
	
	// Parameters used for adjusting fonts and layout for displaying and printing addresses
	private static final Font SCREEN_FONT 			= new Font(ReceiptPrinterProperties.getProperty("addressTable.fontName"), 
															   Font.PLAIN, 
															   Integer.valueOf(ReceiptPrinterProperties.getProperty("addressTable.screenFontSize")));
	private static final Font SCREEN_HEADER_FONT 	= new Font(ReceiptPrinterProperties.getProperty("addressTable.fontName"), 
															   Font.PLAIN, 
															   Integer.valueOf(ReceiptPrinterProperties.getProperty("addressTable.screenHeaderFontSize")));
	private static final Font PRINT_FONT  			= new Font(ReceiptPrinterProperties.getProperty("addressTable.fontName"), 
															   Font.PLAIN, 
															   Integer.valueOf(ReceiptPrinterProperties.getProperty("addressTable.printFontSize")));
	private static final Font PRINT_HEADER_FONT  	= new Font(ReceiptPrinterProperties.getProperty("addressTable.fontName"), 
															   Font.PLAIN, 
															   Integer.valueOf(ReceiptPrinterProperties.getProperty("addressTable.printHeaderFontSize")));
	
	private static final int[] SCREEN_COLUMN_PARAMS = {Integer.valueOf(ReceiptPrinterProperties.getProperty("addressTable.screenStateColumnWidth")),
													   Integer.valueOf(ReceiptPrinterProperties.getProperty("addressTable.screenZipColumnWidth")),
													   Integer.valueOf(ReceiptPrinterProperties.getProperty("addressTable.screenRowHeight"))};
	
	private static final int[] PRINT_COLUMN_PARAMS  = {Integer.valueOf(ReceiptPrinterProperties.getProperty("addressTable.printStateColumnWidth")),
		   											   Integer.valueOf(ReceiptPrinterProperties.getProperty("addressTable.printZipColumnWidth")),
		   											   Integer.valueOf(ReceiptPrinterProperties.getProperty("addressTable.printRowHeight"))};

	private TableCellRenderer screenTableCellRenderer;
	private TableCellRenderer printTableCellRenderer;


	public AddressTable() {
		screenTableCellRenderer = new DefaultTableCellRenderer();
		printTableCellRenderer  = new TextTableRenderer(PRINT_FONT);
		
		setShowGrid(true);
		setGridColor(Color.black);
		setModel(new AddressTableModel());
		setToolTipText(ReceiptPrinterProperties.getProperty("table.deleteTooltip"));
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setPreferredScrollableViewportSize(new Dimension(500, 70));
		setFillsViewportHeight(true);
		setTableLook(SCREEN_COLUMN_PARAMS, SCREEN_FONT, SCREEN_HEADER_FONT, screenTableCellRenderer);
		getTableHeader().setBackground(Color.black);
		getTableHeader().setForeground(Color.white);
		addKeyListener(new AddressTableKeyListener(this));
	}
	
	/**
	 * Repopulates the AddressTable
	 */
	public void repopulate() {
		setModel(new AddressTableModel());
		setTableLook(SCREEN_COLUMN_PARAMS, SCREEN_FONT, SCREEN_HEADER_FONT, screenTableCellRenderer);
		repaint();
	} 

	/**
	 * Expands the AddressTable for printing and then sets it back to a size appropriate for the screen
	 */
	public void printAddresses() {
		setTableLook(PRINT_COLUMN_PARAMS, PRINT_FONT, PRINT_HEADER_FONT, printTableCellRenderer);
		try {
			print(PrintMode.FIT_WIDTH, new MessageFormat("Receipt Printer Addresses"), null);
		} catch (PrinterException ex) {
			ReceiptPrinterLogger.logMessage(this.getClass(), Level.WARNING, "PrinterException while attempting to print addresses", ex);
		}
		setTableLook(SCREEN_COLUMN_PARAMS, SCREEN_FONT, SCREEN_HEADER_FONT, screenTableCellRenderer);
	}

	/**
	 * Sets how the table looks based on if the table is being used for printing or displaying on the screen 
	 * @param columnParams Params for columns and rows
	 * @param font Main font used in the table
	 * @param headerFont Font used for the header of the table
	 * @param tableCellRenderer
	 */
	void setTableLook(int[] columnParams, Font font, Font headerFont, TableCellRenderer tableCellRenderer) {
		
		for (int x = 0; x < AddressTabColumns.values().length; x++) {
			getColumnModel().getColumn(x).setCellRenderer(tableCellRenderer);
		}

		setFont(font);
		getTableHeader().setFont(headerFont);

		getColumnModel().getColumn(AddressTabColumns.STATE.getColumn()).setMaxWidth(columnParams[0]);
		getColumnModel().getColumn(AddressTabColumns.ZIP.getColumn()).setMaxWidth(columnParams[1]);

		setRowHeight(columnParams[2]);
	}
}
