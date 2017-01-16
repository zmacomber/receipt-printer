package com.mbc.receiptprinter.ui.print.setup;

import javax.swing.*;
import java.awt.*;

public class TopSeparator extends JSeparator {

    public TopSeparator() {
        setSize(new Dimension(0, 10));
        setForeground(new Color(0, 0, 0));
        setBounds(30, 71, 540, 2);
    }
}
