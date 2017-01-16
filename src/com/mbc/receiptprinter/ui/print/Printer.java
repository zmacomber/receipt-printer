package com.mbc.receiptprinter.ui.print;

import com.mbc.receiptprinter.ui.print.setup.ReceiptGraphics;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;

import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;

public class Printer {

    private final ReceiptPrintOut receiptPrintOut;

    Printer(ReceiptPrintOut receiptPrintOut) {
        this.receiptPrintOut = receiptPrintOut;
    }

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

		/* Now print the window and its visible contents */
        receiptPrintOut.print(ReceiptGraphics.setup(g, pf));

		/* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
}
