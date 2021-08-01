<%@ page import="by.voluevich.entity.MathOperation" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: A E S T H E T I C
  Date: 30.07.2021
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>History operation</h1>
<form action="/log" method="post">
    <select required name="type" size="?">
        <option disabled>Select type operation</option>
        <option value="addition">addition</option>
        <option value="division">division</option>
        <option value="modulo">modulo</option>
        <option value="multiplication">multiplication</option>
        <option value="subtraction">subtraction</option>
    </select>
    <button>Get log by operation</button>
</form>
<form action="/log" method="post">
    <input type="hidden" name="logBySession" value="true" readonly>
    <button>Get log by session</button>
</form>
<form action="/log" method="post">
    <button>Get log all user</button>
</form>
<form action="/mathOperation">
    <button>Back to calculator</button>
</form>
<p>${requestScope.log_list}</p>
</body>
</html>
