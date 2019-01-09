package com.mbc.receiptprinter.ui.print.setup;

import com.mbc.receiptprinter.util.ReceiptPrinterProperties;

import java.awt.*;
import java.awt.print.PageFormat;

public class ReceiptGraphics {

    public static Graphics setup(Graphics g, PageFormat pf) {
        /*
		 * User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping
		 */
        int xOffset = Integer.valueOf(ReceiptPrinterProperties.getProperty("receipt.printout.x.offset"));
        int yOffset = Integer.valueOf(ReceiptPrinterProperties.getProperty("receipt.printout.y.offset"));
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX() + xOffset, pf.getImageableY() + yOffset);

        return g;
    }
}
