package com.apress.pss.webflow.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.webflow.engine.State;

public class StateSecurityExpressionRoot extends SecurityExpressionRoot {
	private State state;
    public StateSecurityExpressionRoot(Authentication a, State state) {
        super(a);
        this.state = state;
    }

}
