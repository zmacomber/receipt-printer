package com.mbc.receiptprinter.ui.print.setup;

import javax.swing.*;
import java.awt.*;

public class ReceiptDate extends JLabel {

    public ReceiptDate(String receiptDate) {
        super(receiptDate);
        setFont(new Font("Times New Roman", Font.PLAIN, 12));
        setBounds(444, 25, 79, 20);
    }
}
