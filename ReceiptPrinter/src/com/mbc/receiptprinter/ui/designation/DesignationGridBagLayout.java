package com.mbc.receiptprinter.ui.designation;

import java.awt.GridBagLayout;

public class DesignationGridBagLayout extends GridBagLayout {

	private static final long serialVersionUID = 1L;

	public DesignationGridBagLayout() {
		columnWidths = new int[]{200, 50, 100, 100, 100, 200};
		columnWeights = new double[]{1.0, 0.2, 0.3, 0.0, 0.5, 1.0};
		
		rowHeights = new int[]{10, 0, 0, 0, 200};
		rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.1};
	}
}
