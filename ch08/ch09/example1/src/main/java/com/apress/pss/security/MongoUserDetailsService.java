package com.apress.pss.security;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

public class MongoUserDetailsService implements UserDetailsManager {

	private MongoTemplate mongoTemplate;
	
	public MongoUserDetailsService(MongoTemplate mongoTemplate){
		this.mongoTemplate = mongoTemplate;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails user = mongoTemplate.findOne(new Query(Criteria.where("username").is(username)),
				User.class, "users");
		if (user == null){
			throw new UsernameNotFoundException("Username "+username+ " not found.");
		}
		return user;
	}

	public void createUser(UserDetails user) {
		mongoTemplate.insert(user, "users");
		
	}

	public void updateUser(UserDetails user) {
		throw new UnsupportedOperationException();
	}

	public void deleteUser(String username) {
		throw new UnsupportedOperationException();
	}

	public void changePassword(String oldPassword, String newPassword) {
		throw new UnsupportedOperationException();
	}

	public boolean userExists(String username) {
		throw new UnsupportedOperationException();
	}

}
