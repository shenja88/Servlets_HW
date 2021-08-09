<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: A E S T H E T I C
  Date: 04.08.2021
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<h1 align="center">Sign in</h1>
<form align="center" action="/login" method="post">
    <input type="text" name="login" placeholder="login" required>
    <input type="text" name="password" placeholder="password" required>
    <button type="submit">Sign in</button>
</form>
<c:if test="${requestScope.message_signIn != null}">
    <p align="center">${requestScope.message_signIn}</p>
</c:if>
<p align="center"><a href="/main">Back to main</a></p>
</body>
</html>
