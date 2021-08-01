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
<h1>Sign in</h1>
<form action="/registration" method="post">
    <input type="text" name="login" placeholder="login" required>
    <input type="text" name="password" placeholder="password" required>
    <button>Get registration</button>
</form>
<p>${requestScope.message_reg}</p>
<form action="/login" method="post">
    <input type="text" name="login" placeholder="login">
    <input type="text" name="password" placeholder="password">
    <button>Sign in</button>
</form>
<p>${requestScope.message_signIn}</p>
</body>
</html>
