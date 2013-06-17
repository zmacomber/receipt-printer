package com.mbc.receiptprinter.ui.constraints;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ReceiptPrinterConstraints extends GridBagConstraints {

	private static final long serialVersionUID = 1L;
	
	public static class Builder {
		
		// Initialized to default values
		private int gridwidth 	= 1;
		private int gridheight 	= 1;
		private int fill 	  	= NONE; 
		private int gridx	  	= RELATIVE; 
		private int gridy	  	= RELATIVE; 
		private int anchor	  	= CENTER;
		private double weighty	= 0.0;
		private double weightx	= 0.0;
		
		public Builder gridwidth(int val) 	{ gridwidth  = val; return this; }
		public Builder gridheight(int val) 	{ gridheight = val; return this; }
		public Builder fill(int val) 		{ fill 		 = val; return this; }
		public Builder gridx(int val) 		{ gridx 	 = val; return this; }
		public Builder gridy(int val) 		{ gridy 	 = val; return this; }
		public Builder anchor(int val) 		{ anchor 	 = val; return this; }
		public Builder weighty(double val)	{ weighty	 = val; return this; }
		public Builder weightx(double val)	{ weightx	 = val; return this; }
				
		public ReceiptPrinterConstraints build() { return new ReceiptPrinterConstraints(this); }
	}
	
	private ReceiptPrinterConstraints(Builder builder) {
		insets 		= new Insets(0, 0, 5, 5);
		gridwidth 	= builder.gridwidth;
		gridheight  = builder.gridheight;
		fill 		= builder.fill;
		gridx 		= builder.gridx;
		gridy 		= builder.gridy;
		anchor 		= builder.anchor;
		weighty	    = builder.weighty;
		weightx	    = builder.weightx;
	}
}
