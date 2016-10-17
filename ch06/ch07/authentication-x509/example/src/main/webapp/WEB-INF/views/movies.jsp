<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Terror movies</title>
</head>
<body>
	Mr &nbsp;<security:authentication property="principal.lastname"/>
	<br/><br/>
	<c:forEach items="${movies}" var="movie">	
		<security:authorize access="hasRole('ROLE_USER')" var="isUser">
			${movie.name}<br />
		</security:authorize>
		<security:authorize access="hasRole('ROLE_VIP')" var="isVip">
			${movie.budget}<br />
		</security:authorize>
	</c:forEach>
	<c:if test="${isVip}">
		You are a very appreciated VIP user
	</c:if>
</body>
</html>