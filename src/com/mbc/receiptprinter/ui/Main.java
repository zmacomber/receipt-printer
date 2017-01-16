package com.mbc.receiptprinter.ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.mbc.receiptprinter.util.ReceiptPrinterUIUtils;

/**
 * Entry point for the program
 */
public class Main {

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame(ReceiptPrinterUIUtils.MAIN_FRAME_NAME);
		frame.setName(ReceiptPrinterUIUtils.MAIN_FRAME_NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new MainTabbedPane());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	}
}