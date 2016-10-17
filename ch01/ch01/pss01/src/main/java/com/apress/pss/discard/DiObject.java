package com.apress.pss.discard;

public class DiObject {
	private Helper helper;

	public DiObject(Helper helper) {
		this.helper = helper;
	}
	
	public void doStuffWithHelp(){
		helper.help();
	}
}
