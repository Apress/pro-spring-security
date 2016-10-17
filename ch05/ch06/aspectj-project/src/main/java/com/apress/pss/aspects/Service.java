package com.apress.pss.aspects;

import org.springframework.security.access.annotation.Secured;

@org.springframework.stereotype.Service
public class Service {
	public String  methodA() {
		return methodB();
	}

	@Secured("ROLE_USER")
	private String methodB() {
		return "AspectJ Secured String";
	}
}
