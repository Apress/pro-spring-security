package com.apress.pss.security;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

public class UsernameVoter implements AccessDecisionVoter<Object> {

	public boolean supports(ConfigAttribute attribute) {
		if ((attribute.getAttribute() != null)
				&& attribute.getAttribute().startsWith("USERNAME_")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public int vote(Authentication authentication, Object object,
			Collection<ConfigAttribute> attributes) {
		int result = ACCESS_ABSTAIN;
		String username = authentication.getName();
		for (ConfigAttribute attribute : attributes) {
			if (this.supports(attribute)) {
				result = ACCESS_DENIED;
				String stringedAttribute = attribute.getAttribute();
				String shortedAttribute = attribute.getAttribute().substring(stringedAttribute.indexOf("_")+1, stringedAttribute.length());
				if (shortedAttribute.equalsIgnoreCase(username)) {
					return ACCESS_GRANTED;
				}
			}
		}
		return result;
	}

}
