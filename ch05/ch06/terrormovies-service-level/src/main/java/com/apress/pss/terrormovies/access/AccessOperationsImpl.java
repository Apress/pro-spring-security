package com.apress.pss.terrormovies.access;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

public class AccessOperationsImpl implements AccessOperations, ApplicationContextAware {

	private AuthenticationManager authenticationManager;
	private ApplicationContext context;
	
	public AccessOperationsImpl(){
		SecurityContextHolder.setStrategyName("MODE_GLOBAL");
		initAnonymous();

	}

	public void login(String username, String password) {
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);
		 Authentication authentication = authenticationManager.authenticate(authRequest);
		 SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	public void logout() {
		SecurityContextHolder.clearContext();
		initAnonymous();
	}

	public String executeOperation(String beanName, String method) {
		Object bean =  context.getBean(beanName);
		Object result = null;
		try {
			result = bean.getClass().getMethod(method).invoke(bean);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result.toString();
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	private void initAnonymous() {
		AnonymousAuthenticationToken auth = new AnonymousAuthenticationToken("anonymous", "anonymousUser", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
