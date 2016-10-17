package com.apress.pss.webflow.security;

import org.springframework.security.access.expression.AbstractSecurityExpressionHandler;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.webflow.engine.State;

public class DefaultFlowStateSecurityExpressionHandler extends AbstractSecurityExpressionHandler<State>{

	@Override
	protected SecurityExpressionRoot createSecurityExpressionRoot(
			Authentication authentication, State invocation) {
		SecurityExpressionRoot root = new StateSecurityExpressionRoot(authentication, invocation);
        root.setPermissionEvaluator(getPermissionEvaluator());
        return root;
	}

}
