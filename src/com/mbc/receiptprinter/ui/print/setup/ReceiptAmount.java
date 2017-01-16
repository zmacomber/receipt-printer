package com.mbc.receiptprinter.ui.print.setup;

import com.mbc.receiptprinter.bean.Receipt;

import javax.swing.*;
import java.awt.*;

public class ReceiptAmount extends JLabel {

    public ReceiptAmount(Receipt receipt) {
        super("Amount:      $      " + receipt.getAmount());
        setFont(new Font("Serif", Font.PLAIN, 12));
        setBounds(366, 111, 157, 40);
    }
}
