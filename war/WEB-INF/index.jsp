<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>アバター旅行記 - blog登録</title>
</head>
<body>
<form action="/index" method="post">
<h1>アバター旅行記</h1>

<div style="float: left;">
<h2>１．アバターの選択</h2>
<table>
  <tr>
    <c:forEach var="avatar" varStatus="status" items="${avatars}">
      <td><img src="/img/<c:out value="${avatar.imageName}" />" /><br />
      <label><input type="radio" name="avatarId"
        value="<c:out value="${avatar.id}" />"
        <c:if test="${status.first}">checked="checked"</c:if> /><span><c:out
        value="${avatar.name}" /></span></label></td>
    </c:forEach>
  </tr>
</table>
</div>

<div style="float: left;">
<h2>２．行き先の選択</h2>
<p>行き先 <select name="destination">
    <option value="Prague">プラハ</option>
    <option value="London">ロンドン</option>
    <option value="Roma">ローマ</option>
    <option value="New York">ニューヨーク</option>
    <option value="Las Vegas">ラスベガス</option>
    <option value="San Francisco">サンフランシスコ</option>
    <option value="Giza">ギザ</option>
    <option value="Dubai">ドバイ</option>
  </select></p>

<h2>３．出発</h2>
<p>アバターと行き先を選択したら、このボタン→<input type="submit" value="出発" />をクリックしてください</p>

<h2>最近の旅行記</h2>
<ul>
  <c:forEach var="blog" items="${blogs}">
    <li><a href='/blog/<c:out value="${blog.id}" />'><c:out
      value="${blog.avatar.name}" /> in <c:out
      value="${blog.destination}" />（出発日：<fmt:formatDate
      value="${blog.departureDate}" pattern="yyyy/MM/dd" />）</a></li>
  </c:forEach>
</ul>
</div>
</form>
</body>
</html>