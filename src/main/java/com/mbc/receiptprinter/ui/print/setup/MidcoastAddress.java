package com.mbc.receiptprinter.ui.print.setup;

import javax.swing.*;
import java.awt.*;

public class MidcoastAddress extends JTextPane {

    public MidcoastAddress() {
        setBounds(36, 0, 193, 45);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setFont(new Font("Times New Roman", Font.PLAIN, 12));
        setEditable(false);
        setText("Mid-Coast Baptist Church\r\nP.O. Box 537\r\nBrunswick, ME 04011-0537");
    }
}

