<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: A E S T H E T I C
  Date: 30.07.2021
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignIn</title>
</head>
<body>
<h1 align="center">Registration</h1>
<form align="center" action="/registration" method="post">
    <input type="text" name="name" placeholder="name" required>
    <input type="text" name="login" placeholder="login" required>
    <input type="text" name="password" placeholder="password" required>
    <button type="submit">Get registration</button>
</form>
<c:if test="${requestScope.message_reg != null}">
    <p align="center">${requestScope.message_reg}</p>
</c:if>
<p align="center"><button onclick='location.href="/main"'>Back to main</button></p>
</body>
</html>