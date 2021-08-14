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
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1 align="center">Sign in</h1>
<form align="center" action="/login" method="post">
    <label for="log">Login</label>
    <input id="log" type="text" name="login" required>
    <label for="pass"></label>
    <input id="pass" type="text" name="password" required>
    <button class="button_submit" type="submit">Sign in</button>
</form>
<c:if test="${requestScope.message_signIn != null}">
    <p align="center">${requestScope.message_signIn}</p>
</c:if>
<a class="my_href" href="/main">Back to main</a>
</body>
</html>
