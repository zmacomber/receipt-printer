package com.mbc.receiptprinter.util;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFrame;

public class ReceiptPrinterUIUtils {
	
	public static final String MAIN_FRAME_NAME = "ReceiptPrinter";

	private static final Font defaultFont;
	static {
        Font font = null;

        try {
            font = new Font(ReceiptPrinterProperties.getProperty("receiptPrinter.default.fontName"), 
                                   Font.PLAIN, 
                                   Integer.valueOf(ReceiptPrinterProperties.getProperty("receiptPrinter.default.fontSize")));
        } catch (Exception e) {
            font = new Font("Tahoma", Font.PLAIN, 12);
        }

        defaultFont = font;
	}
	
	public static Frame getMainFrame() {
		Frame mainFrame = null;
		for (Frame f : JFrame.getFrames()) {
			if (f.getName().equals(MAIN_FRAME_NAME)) {
				mainFrame = f;
				break;
			}
		}
		return mainFrame;
	}
	
	public static Font getDefaultFont() {
		return defaultFont;
	}
}
