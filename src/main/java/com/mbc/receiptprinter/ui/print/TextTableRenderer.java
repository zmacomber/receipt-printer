package com.mbc.receiptprinter.ui.print;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Used for rendering a table properly for printing
 */
public class TextTableRenderer extends JTextArea implements TableCellRenderer {

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
