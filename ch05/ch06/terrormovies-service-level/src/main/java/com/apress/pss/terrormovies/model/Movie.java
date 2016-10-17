package com.apress.pss.terrormovies.model;

public class Movie {
	private String name;
	private String budget;
	
	
	public Movie(String name, String budget) {
		super();
		this.name = name;
		this.budget = budget;
	}
	
	public String getName() {
		return name;
	}

	public String getBudget() {
		return budget;
	}	
	
	public String toString(){
		return "Title: "+name+"; Budget: "+budget;
	}
	
}
