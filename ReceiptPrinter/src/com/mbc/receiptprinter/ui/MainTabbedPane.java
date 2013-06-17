package com.mbc.receiptprinter.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mbc.receiptprinter.ui.tabs.AddressTab;
import com.mbc.receiptprinter.ui.tabs.DesignationTab;
import com.mbc.receiptprinter.ui.tabs.ReceiptTab;
import com.mbc.receiptprinter.ui.tabs.TotalYearlyAmountReportTab;
import com.mbc.receiptprinter.util.ReceiptPrinterProperties;

public class MainTabbedPane extends JPanel {

	private static final long serialVersionUID = 4837233552147889889L;

	public MainTabbedPane() {
		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font(ReceiptPrinterProperties.getProperty("mainTabbedPane.fontName"), 
									Font.PLAIN, 
									Integer.valueOf(ReceiptPrinterProperties.getProperty("mainTabbedPane.fontSize"))));

		JComponent addressTab = new AddressTab();
		tabbedPane.addTab("Addresses", addressTab);

		JComponent designationTab = new DesignationTab();
		tabbedPane.addTab("Designations", designationTab);

		JComponent receiptTab = new ReceiptTab();
		tabbedPane.addTab("Receipts", receiptTab);

		JComponent panel4 = new TotalYearlyAmountReportTab();
		panel4.setPreferredSize(new Dimension(410, 50));
		tabbedPane.addTab("Total Yearly Amount Report", panel4);

		// Add the tabbed pane to this panel.
		add(tabbedPane);

		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (e.getSource() instanceof JTabbedPane) {
					Component c = ((JTabbedPane) e.getSource()).getSelectedComponent();
					if (c instanceof ReceiptTab) {
						((ReceiptTab) c).refreshComboBoxes();
					}
					if (c instanceof TotalYearlyAmountReportTab) {
						((TotalYearlyAmountReportTab) c).refreshComboBoxes();
					}
				}
			}
		});
	}
}
