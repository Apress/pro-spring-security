package com.apress.pss.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CookieAccessDeniedHandler implements AccessDeniedHandler {

    private static final String ACCESS_DENIES = "access_denies";

    private String errorPage;
    
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
        if (!response.isCommitted()) {
        	response.addCookie(new Cookie(ACCESS_DENIES, String.valueOf(getDeniesCookie(request)+1)));
            if (errorPage != null) {
                request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
            }
        }
		
	}
	
	private int getDeniesCookie(HttpServletRequest request) {
		for(Cookie cookie:request.getCookies()){
			if(cookie.getName().equals(ACCESS_DENIES)){
				return Integer.parseInt(cookie.getValue());
			}
		}
		return 0;
	}

}
