package com.mbc.receiptprinter.ui.print.setup;

import com.mbc.receiptprinter.ui.print.ReceiptPrintOut;

import java.awt.*;

public class OverallStructure {

    public static void setup(ReceiptPrintOut receiptPrintOut) {
        receiptPrintOut.setResizable(false);
        receiptPrintOut.getContentPane().setBackground(Color.WHITE);
        receiptPrintOut.setBackground(Color.WHITE);
        receiptPrintOut.getContentPane().setLayout(null);
    }
}
