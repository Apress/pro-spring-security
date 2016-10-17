package com.apress.pss.aspects.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apress.pss.aspects.Service;

@Controller
@RequestMapping("/hello")
public class TheController {
	
	@Autowired
	private Service service;
	
	@RequestMapping("/")
	public String doSomething(){
		return service.methodA();
	}
}
