<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/blog.css">
<title>アバター旅行記</title>
</head>
<body>
<div id="header">
<h1>アバター旅行記</h1>
<div id="container">

<%-- ブログに含まれる記事の一覧を表示する --%>
<div id="contents"><c:if test="${fn:length(blog.articles) == 0}">
  <p>（現在、目的地に向かって移動中です。ちょっと待ってください）</p>
</c:if><c:forEach var="article" items="${blog.articles}">
  <div class="article">
  <div>投稿日：<fmt:formatDate value="${article.postDate}"
    pattern="yyyy/MM/dd HH:mm" timeZone="Asia/Tokyo" /></div>
  <%-- 記事に写真が登録されているときのみ、写真を表示する --%>
  <c:if test="${article.imageUrl != null && article.imageUrl != ''}">
    <div><a href="<c:out value="${article.pageUrl}" />"><img
      src="<c:out value="${article.imageUrl}" />" /></a></div>
  </c:if>
  <div><c:out value="${article.text}" /></div>
  </div>
</c:forEach>
<%-- 次の投稿予定日時を表示する（比較値は約9999年） --%>
<c:if test="${blog.nextPostDate.time < 30000000000000}">
  <p>次の投稿予定：<fmt:formatDate value="${blog.nextPostDate}"
    pattern="yyyy/MM/dd HH:mm" timeZone="Asia/Tokyo" /> ごろ</p>
</c:if></div>

<%-- 旅行の情報を表示する --%>
<div id="navigation">
<table>
  <tr>
    <th>名前</th>
    <td><c:out value="${blog.avatar.name}" /></td>
  </tr>
  <tr>
    <th>出発日</th>
    <td><fmt:formatDate value="${blog.departureDate}"
    pattern="yyyy/MM/dd" /></td>
  </tr>
  <tr>
    <th>目的地</th>
    <td><c:out value="${blog.destination}" /></td>
  </tr>
  <tr>
    <td colspan="2">
    <img src="/img/<c:out value="${blog.avatar.imageName}" />" /></td>
  </tr>
</table>
</div>
</div>
</body>
</html>