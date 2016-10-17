package com.apress.pss.security;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

public class URLAccessDecisionManager implements AccessDecisionManager{

	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		FilterInvocation invocation = (FilterInvocation)object;
		String url = invocation.getRequestUrl().replaceAll("/", "");
		boolean granted = false;
		for(GrantedAuthority authority:authentication.getAuthorities()){
			if (authority.getAuthority().equals(url)){
				granted = true;
				break;
			}
		}
		if(!granted){
		  throw new AccessDeniedException("Access denied");
		}
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}
