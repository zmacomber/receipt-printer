package com.mbc.receiptprinter.constant;

import com.mbc.receiptprinter.util.ReceiptPrinterStringUtils;

/**
* United States names and postal abbreviations
*/
public enum StateCode {

	AL("Alabama"), AK("Alaska"), AZ("Arizona"), AR("Arkansas"), CA("California"), CO("Colorado"), CT("Connecticut"), DE("Delaware"), FL("Florida"), GA("Georgia"), HI("Hawaii"), ID("Idaho"), IL("Illinois"), 
	IN("Indiana"), IA("Iowa"), KS("Kansas"), KY("Kentucky"), LA("Louisiana"), ME("Maine"), MD("Maryland"), MA("Massachusetts"), MI("Michigan"), MN("Minnesota"), MS("Mississippi"), MO("Missouri"), 
	MT("Montana"), NE("Nebraska"), NV("Nevada"), NH("New Hampshire"), NJ("New Jersey"), NM("New Mexico"), NY("New York"), NC("North Carolina"), OH("Ohio"), OK("Oklahoma"), OR("Oregon"), PA("Pennsylvania"), 
	RI("Rhode Island"), SC("South Carolina"), SD("South Dakota"), TN("Tennessee"), TX("Texas"), UT("Utah"), VT("Vermont"), VA("Virginia"), 
	WA("Washington"), DC("Washington-DC"), WV("West Virginia"), WI("Wisconsin"), WY("Wyoming");
	
	private String name;
	private StateCode(String name) 		{ this.name = name; }
	public String getName() 	   	{ return name;      }
	public void setName(String name) 	{ this.name = name; }

	/**
	* Gets all of the United States names
	* @return A String array of all the United States names
	*/
	public static String[] getNames() {
		String[] names = new String[StateCode.values().length];
		int counter = 0;
		for (StateCode code : StateCode.values()) {
			names[counter] = code.getName();
			counter++;
		}
		return names;
	}

	/**
	* Gets the postal abbreviation of the supplied name
	* @param name The name of a state
	* @return The postal abbreviation associated with the name param.  If the name is not found, a blank String will be returned.
	*/	
	public static String getCodeByName(String name) {
		if (ReceiptPrinterStringUtils.isNullOrEmpty(name)) { return ""; }
		for (StateCode stateCode : StateCode.values()) {
			if (stateCode.getName().equals(name)) { return stateCode.toString(); }
		}
		return "";
	}
}
