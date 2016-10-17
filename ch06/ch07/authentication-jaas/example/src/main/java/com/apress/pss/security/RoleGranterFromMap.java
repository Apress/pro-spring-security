package com.apress.pss.security;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.security.authentication.jaas.AuthorityGranter;

public class RoleGranterFromMap implements AuthorityGranter {

	private static Map<String, String> USER_ROLES = new HashMap<String, String>();

	static {
		USER_ROLES.put("car", "ROLE_ADMINISTRATOR");
	}
	
	public Set<String> grant(Principal principal) {
		return Collections.singleton(USER_ROLES.get(principal.getName()));
	}
}
