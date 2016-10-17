package com.apress.pss.terrormovies.access;

public interface AccessOperations {
	void login(String username, String password);
	void logout();
	String executeOperation(String beanName, String method);
}
