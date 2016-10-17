package com.apress.pss.discard;

public class BusinessObjectTransactionalDecorator implements BusinessThing {
	private BusinessThing component;
	
	public BusinessObjectTransactionalDecorator(BusinessThing component){
		this.component = component;
	}
	public void doBusinessThing() {
		//some start transaction code
		component.doBusinessThing();
		//some commit transaction code
	}

}
