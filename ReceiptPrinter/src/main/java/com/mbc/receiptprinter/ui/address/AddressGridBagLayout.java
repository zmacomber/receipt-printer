package com.mbc.receiptprinter.ui.address;

import java.awt.GridBagLayout;

public class AddressGridBagLayout extends GridBagLayout {

	private static final long serialVersionUID = 1L;

	public AddressGridBagLayout() {
		columnWidths = new int[]{10, 50, 100, 100, 400, 10};
		columnWeights = new double[]{0.2, 0.2, 0.3, 0.0, 0.5, 0.2};
		
		rowHeights = new int[]{10, 0, 0, 0, 0, 0, 0, 0, 0, 200};
		rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.1};
	}
}
