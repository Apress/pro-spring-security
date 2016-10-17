package com.apress.pss.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class UserWriteConverter implements Converter<User,DBObject> {

	public DBObject convert(User source) {
		DBObject dbObject =  new BasicDBObject();
		dbObject.put("username", source.getUsername());
		dbObject.put("password", source.getPassword());
		dbObject.put("authorities", authortiesAsStringArray(source));
		return dbObject;
	}

	private Object authortiesAsStringArray(User source) {
		String[] authorities = new String[source.getAuthorities().size()];
		int i = 0;
		for(GrantedAuthority auth: source.getAuthorities()){
			authorities[i]=auth.getAuthority();
			i++;
		}
		return authorities;
	}

}
