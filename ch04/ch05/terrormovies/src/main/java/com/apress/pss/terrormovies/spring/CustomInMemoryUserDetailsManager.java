package com.apress.pss.terrormovies.spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.apress.pss.terrormovies.model.User;

public class CustomInMemoryUserDetailsManager implements UserDetailsService {

	private Map<String, User> users = new HashMap<String, User>();

	public CustomInMemoryUserDetailsManager(Collection<User> users) {
		for (User user : users) {
			this.users.put(user.getUsername().toLowerCase(), user);
		}
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = users.get(username.toLowerCase());

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		User userNew = new User(user.getUsername(), user.getPassword(), user.getAuthorities(), user.getLastname(),user.getAge());
		
		return userNew;
	}

}
