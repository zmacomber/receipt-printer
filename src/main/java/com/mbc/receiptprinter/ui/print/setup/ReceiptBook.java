package com.mbc.receiptprinter.ui.print.setup;

import com.mbc.receiptprinter.ui.print.ReceiptPrintOut;

import java.awt.print.Book;

public class ReceiptBook extends Book {

    public ReceiptBook(ReceiptPrintOut receiptPrintOut) {
        append(receiptPrintOut, new ReceiptPageFormat());
    }
}
