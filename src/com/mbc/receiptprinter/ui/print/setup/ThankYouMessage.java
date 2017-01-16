package com.mbc.receiptprinter.ui.print.setup;

import javax.swing.*;
import java.awt.*;

public class ThankYouMessage extends JLabel {

    public ThankYouMessage() {
        super("Thank you for your investment in church planting.");
        setForeground(new Color(0, 0, 0));
        setFont(new Font("Tahoma", Font.PLAIN, 14));
        setBounds(137, 265, 318, 40);
    }
}
