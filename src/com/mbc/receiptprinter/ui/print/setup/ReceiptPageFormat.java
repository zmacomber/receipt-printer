package com.mbc.receiptprinter.ui.print.setup;

import com.mbc.receiptprinter.util.ReceiptPrinterProperties;

import java.awt.print.PageFormat;
import java.awt.print.Paper;

class ReceiptPageFormat extends PageFormat {

    ReceiptPageFormat() {
        Paper paper = new Paper();
        paper.setImageableArea(0, 0, Integer.valueOf(ReceiptPrinterProperties.getProperty("receipt.printout.area.width")),
                Integer.valueOf(ReceiptPrinterProperties.getProperty("receipt.printout.area.height")));

        setOrientation(PageFormat.PORTRAIT);
        setPaper(paper);
    }
}
