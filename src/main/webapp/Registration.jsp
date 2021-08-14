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
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1 align="center">Registration</h1>
<form align="center" action="/registration" method="post">
    <label for="name">name</label>
    <input id="name" type="text" name="name" required>
    <label for="log">login</label>
    <input id="log" type="text" name="login" required>
    <label for="pass">password</label>
    <input id="pass" type="text" name="password" required>
    <button class="button_submit" type="submit">Get registration</button>
</form>
<c:if test="${requestScope.message_reg != null}">
    <p align="center">${requestScope.message_reg}</p>
</c:if>
<a class="my_href" href="/main">Back to main</a>
</body>
</html>