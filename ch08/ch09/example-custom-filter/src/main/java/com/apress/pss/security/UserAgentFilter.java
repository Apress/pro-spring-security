package com.apress.pss.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class UserAgentFilter extends GenericFilterBean{

	private static final String ADMIN_ROLE = "ROLE_ADMIN";
	private static final String FIREFOX_AGENT_CONTAINS = "Firefox";
	private AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(((HttpServletRequest)request).getHeader("User-Agent").contains(FIREFOX_AGENT_CONTAINS)){
			chain.doFilter(request, response);	
		}else{
			processRequestWhenNotExpectedUserAgent(request,response,chain);
		}    		
	}
	
	private void processRequestWhenNotExpectedUserAgent(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(isUserAnAdmin() || isUserAnonymous()){
			chain.doFilter(request, response);	
		}else{
			throw new AccessDeniedException("The browser you are using is not supported for your user role. Use Firefox instead");
		}
	}
	
	private boolean isUserAnonymous() {
		return SecurityContextHolder.getContext().getAuthentication() != null && authenticationTrustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication());
	}

	private boolean isUserAnAdmin() {
		if(SecurityContextHolder.getContext().getAuthentication() != null){
        	for(GrantedAuthority ga : SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
        		if(ga.getAuthority().equals(ADMIN_ROLE)){
        			return true;
        		}
        	}
        }
		return false;
	}

}
