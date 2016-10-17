package com.apress.pss.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

public class UserReadConverter implements Converter<DBObject, User> {

	public User convert(DBObject source) {
		return new User((String) source.get("username"),
				(String) source.get("password"),
				convertAuthoritiesReading(source));
	}

	private Collection<? extends GrantedAuthority> convertAuthoritiesReading(
			DBObject source) {
		List<?> stringAuthorities = (BasicDBList) source.get("authorities");
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Object stringAuth : stringAuthorities) {
			authorities.add(new SimpleGrantedAuthority((String)stringAuth));
		}
		return authorities;
	}
}