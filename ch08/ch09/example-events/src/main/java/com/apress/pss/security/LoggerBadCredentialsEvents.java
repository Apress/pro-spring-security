package com.apress.pss.security;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;

public class LoggerBadCredentialsEvents implements ApplicationListener<AuthenticationFailureBadCredentialsEvent>{

	private static Log LOG = LogFactory.getLog(LoggerBadCredentialsEvents.class);
	public void onApplicationEvent(
			AuthenticationFailureBadCredentialsEvent event) {
		LOG.warn("An attempt to login with bad credentials was made with username "+event.getAuthentication().getName());
		
	}

}
