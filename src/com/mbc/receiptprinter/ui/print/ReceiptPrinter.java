package com.mbc.receiptprinter.ui.print;

import com.mbc.receiptprinter.ui.print.setup.ReceiptBook;
import com.mbc.receiptprinter.util.ReceiptPrinterLogger;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;

public class ReceiptPrinter {

    private final ReceiptBook receiptBook;

    public ReceiptPrinter(ReceiptBook receiptBook) {
        this.receiptBook = receiptBook;
    }

    void print() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(receiptBook);
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
