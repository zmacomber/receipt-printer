package com.mbc.receiptprinter.ui.print.setup;

import com.mbc.receiptprinter.bean.Designation;

import javax.swing.*;
import java.awt.*;

public class DesignationTextPane extends JTextPane {

    public DesignationTextPane(Designation designation) {
        setText("Designation:       " + designation.getName());
        setForeground(Color.BLACK);
        setFont(new Font("Times New Roman", Font.PLAIN, 12));
        setEditable(false);
        setBackground(Color.WHITE);
        setBounds(67, 188, 221, 20);
    }
}
