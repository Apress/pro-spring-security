package com.apress.pss.discard;

public class NonDiObject {
	private Helper helper;
	
	public NonDiObject(){
		helper = new HelperImpl();
	}
	
	public void doStuffWithHelp(){
		helper.help();
	}
}
