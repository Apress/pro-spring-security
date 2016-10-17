package com.apress.pss;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;

public class UserInserter {
	public static void main(String[] args){
		ApplicationContext context = new FileSystemXmlApplicationContext("//Users/cscarioni/projects/pro-spring-security/src/ch09/example1/src/main/webapp/WEB-INF/applicationContext-security.xml");
		UserDetailsManager userDetailsManager = context.getBean(UserDetailsManager.class);
		GrantedAuthority[] authorities = new GrantedAuthority[] {new SimpleGrantedAuthority("ROLE_SCARVAREZ_MEMBER")};
		PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
		UserDetails user = new User("car", encoder.encodePassword("scarvarez",null), Arrays.asList(authorities));
		userDetailsManager.createUser(user);
	}
}

