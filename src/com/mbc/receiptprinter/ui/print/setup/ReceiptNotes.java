package com.mbc.receiptprinter.ui.print.setup;

import com.mbc.receiptprinter.bean.Receipt;

import javax.swing.*;
import java.awt.*;

public class ReceiptNotes extends JTextPane {

    public ReceiptNotes(Receipt receipt) {
        setEditable(false);
        setForeground(Color.BLACK);
        setBackground(Color.WHITE);
        setFont(new Font("Times New Roman", Font.PLAIN, 10));
        setText(receipt.getNotes());
        setBounds(150, 210, 284, 55);
    }
}
