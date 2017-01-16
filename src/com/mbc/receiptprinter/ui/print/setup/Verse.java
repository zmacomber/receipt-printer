package com.mbc.receiptprinter.ui.print.setup;

import com.mbc.receiptprinter.util.ReceiptPrinterProperties;

import javax.swing.*;
import java.awt.*;

public class Verse extends JLabel {

    public Verse() {
        super(ReceiptPrinterProperties.getProperty("receipt.printout.verse"));
        setForeground(new Color(0, 0, 0));

        String verseFontName = ReceiptPrinterProperties.getProperty("receipt.printout.verse.font");
        int verseFontSize = Integer.valueOf(ReceiptPrinterProperties.getProperty("receipt.printout.verse.font.size"));
        setFont(new Font(verseFontName, Font.PLAIN, verseFontSize));

        setBounds(72, 305, 467, 40);
    }
}
