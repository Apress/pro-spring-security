package com.apress.pss.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class AttemptsAuthenticationEntryPoint implements AuthenticationEntryPoint{

	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		response.addHeader("WWW-Authenticate", "Basic realm=\"theapp\"");
		response.addHeader("Set-Cookie", "authentication_attempts="+(getDeniesCookie(request)+1)+"; Max-Age=3600; Version=1");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
       
	}

	private int getDeniesCookie(HttpServletRequest request) {
		for(Cookie cookie:request.getCookies()){
			if(cookie.getName().equals("authentication_attempts")){
				return Integer.parseInt(cookie.getValue());
			}
		}
		return 0;
	}

}
