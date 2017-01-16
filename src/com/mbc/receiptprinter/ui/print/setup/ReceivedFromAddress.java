package com.mbc.receiptprinter.ui.print.setup;

import com.mbc.receiptprinter.bean.Address;
import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

import javax.swing.*;
import java.awt.*;

public class ReceivedFromAddress extends JTextPane {

    public ReceivedFromAddress(Address address) {
        StringBuilder addressText = new StringBuilder();
        addressText.append(address.getName());
        addressText.append("\r\n");
        addressText.append(address.getAddress1());
        addressText.append("\r\n");
        if (ReceiptPrinterStringUtils.isNotNullOrEmpty(address.getAddress2())) {
            addressText.append(address.getAddress2());
            addressText.append("\r\n");
        }
        addressText.append(address.getCity());
        addressText.append(", ");
        addressText.append(address.getStateCode());
        addressText.append(" ");
        addressText.append(address.getZipCode());

        setText(addressText.toString());
        setForeground(new Color(0, 0, 0));
        setFont(new Font("Times New Roman", Font.PLAIN, 12));
        setEditable(false);
        setBackground(Color.WHITE);
        setBounds(67, 122, 221, 65);
    }
}
