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
	<c:forEach items="${posts.keySet()}" var="key">	
			${posts[key].content} <br />
	</c:forEach>
</body>
</html>
