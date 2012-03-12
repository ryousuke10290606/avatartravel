<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>アバター旅行記 - blog登録完了</title>
</head>
<body>
<h1>アバター旅行記</h1>
<div>
<p><img src="/img/<c:out value="${blog.avatar.imageName}" />" /></p>
<p><c:out value="${blog.avatar.name}" />「<c:out value="${blog.avatar.message}" />」</p>
<c:url var="url_blog" value="/blog/${blog.id}" />
<p><a href="<c:out value="${url_blog}" />"><c:out value="${blog.avatar.name}"
 />のblog</a></p>
</div>
</body>
</html>