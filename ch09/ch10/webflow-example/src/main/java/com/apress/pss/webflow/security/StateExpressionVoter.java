package com.apress.pss.webflow.security;

import java.util.Collection;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.webflow.engine.State;

public class StateExpressionVoter implements AccessDecisionVoter<State> {

	private SecurityExpressionHandler<State> expressionHandler = new DefaultFlowStateSecurityExpressionHandler();
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public int vote(Authentication authentication, State object,
			Collection<ConfigAttribute> attributes) {
		 EvaluationContext ctx = expressionHandler.createEvaluationContext(authentication, object);
		 ExpressionParser parser = new SpelExpressionParser();
		 Expression exp = parser.parseExpression(attributes.iterator().next().getAttribute());
		 return ExpressionUtils.evaluateAsBoolean(exp, ctx) ?
	                ACCESS_GRANTED : ACCESS_DENIED;
	}

}
