package com.mbc.receiptprinter.ui.print.setup;

import javax.swing.*;
import java.awt.*;

public class BottomSeparator extends JSeparator {

    public BottomSeparator() {
        setSize(new Dimension(0, 10));
        setForeground(Color.BLACK);
        setBounds(30, 305, 540, 13);
    }
}
