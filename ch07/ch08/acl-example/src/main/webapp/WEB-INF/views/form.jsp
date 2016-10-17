<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Posts</title>
</head>
<body>
     <form method="post" action="/forum/post">
		New Post Content: <input type="text" name="postContent"/><br/>
		<input type="submit"/>
	</form>
	<c:forEach items="${posts}" var="post">
	<security:accesscontrollist  domainObject="${post}" hasPermission="READ">
	   <form method="post" action="/forum/post/delete">	
			${post.content} <br />
			<input type="hidden" value="${post.id}" name="postId"/>
			<input type="submit" value="delete"/>
		</form>	
	</security:accesscontrollist>
	</c:forEach>
</body>
</html>