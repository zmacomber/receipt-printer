package com.mbc.receiptprinter.ui.print.setup;

import javax.swing.*;
import java.awt.*;

public class ReceivedFromLabel extends JLabel {

    public ReceivedFromLabel() {
        super("Received From:");
        setFont(new Font("Times New Roman", Font.BOLD, 14));
        setBounds(37, 84, 132, 20);
    }
}