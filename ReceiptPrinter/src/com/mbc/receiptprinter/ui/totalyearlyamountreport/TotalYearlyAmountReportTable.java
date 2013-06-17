package com.mbc.receiptprinter.ui.totalyearlyamountreport;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.print.PrinterException;

import java.text.MessageFormat;

import java.util.logging.Level;

import com.mbc.receiptprinter.ui.tabs.TotalYearlyAmountReportTabColumns;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;

public class TotalYearlyAmountReportTable extends JTable {
	
	private static final long serialVersionUID = 1L;
	private static final Font SCREEN_FONT = new Font("Times New Roman", Font.PLAIN, 12);
	private static final Font SCREEN_HEADER_FONT = new Font("Times New Roman", Font.PLAIN, 12);
	private static final int  SCREEN_ROW_HEIGHT = 20;
	private static final Font PRINT_FONT = new Font("Times New Roman", Font.PLAIN, 24);
	private static final Font PRINT_HEADER_FONT = new Font("Times New Roman", Font.PLAIN, 18);
	private static final int  PRINT_ROW_HEIGHT = 40;

	public TotalYearlyAmountReportTable(String year) {
		setShowGrid(true);
		setGridColor(Color.black);
		setModel(new TotalYearlyAmountReportTableModel(year));
		setPreferredScrollableViewportSize(new Dimension(500, 70));
		setFillsViewportHeight(true);
		setTableLook(SCREEN_ROW_HEIGHT, SCREEN_FONT, SCREEN_HEADER_FONT);
		getTableHeader().setBackground(Color.black);
		getTableHeader().setForeground(Color.white);
	}
	
	public void repopulate(String year) {
		setModel(new TotalYearlyAmountReportTableModel(year));
		setTableLook(SCREEN_ROW_HEIGHT, SCREEN_FONT, SCREEN_HEADER_FONT);
		repaint();
	} 

	public void printYearlyReport(String year) {
		setTableLook(PRINT_ROW_HEIGHT, PRINT_FONT, PRINT_HEADER_FONT);
		try {
			print(PrintMode.FIT_WIDTH, new MessageFormat("Receipt Printer " + year + " Yearly Report"), null);
		} catch (PrinterException ex) {
			ReceiptPrinterLogger.logMessage(this.getClass(), Level.WARNING, "PrinterException while attempting to print the yearly report", ex);
		}
		setTableLook(SCREEN_ROW_HEIGHT, SCREEN_FONT, SCREEN_HEADER_FONT);
	}

	private void setTableLook(int rowHeight, Font font, Font headerFont) {
		setRowHeight(rowHeight);		
		for (int x = 0; x < TotalYearlyAmountReportTabColumns.values().length; x++) {
			getColumnModel().getColumn(x).setCellRenderer(new TextTableRenderer(font));
		}
		setFont(font);
		getTableHeader().setFont(headerFont);
	}
	
	class TextTableRenderer extends JTextArea implements TableCellRenderer {

		private static final long serialVersionUID = 1L;
	
		public TextTableRenderer(Font font) {		
			setLineWrap(true);
			setWrapStyleWord(true);
			setFont(font);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			setText((value == null) ? "" : value.toString());
			return this;
		}
	}
}
