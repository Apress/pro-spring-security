package com.apress.pss.terrormovies.model;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class User extends org.springframework.security.core.userdetails.User{

	private String lastname;
	private int age;

	public User(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, String lastname, int age) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.lastname = lastname;
		this.age=age;
	}
	
	  public User(String username, String password, Collection<? extends GrantedAuthority> authorities, String lastname, int age) {
	        this(username, password, true, true, true, true, authorities, lastname,age);
	    }

	public String getLastname() {
		return lastname;
	}

	public int getAge() {
		return age;
	}

}

