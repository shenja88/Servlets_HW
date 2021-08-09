<%--
  Created by IntelliJ IDEA.
  User: A E S T H E T I C
  Date: 28.07.2021
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Calculator</title>
</head>
<body background="https://remetc.ru/images/877940.jpg">
<h1 align="center" style="color: floralwhite">Calculator</h1>
<form align="center" action="/mathOperation" method="post">
    <input type="number" step="0.00001" name="num" placeholder="number one">
    <input type="number" step="0.00001" name="num" placeholder="number two">
    <select required name="operation" size="?">
        <option disabled>Select operation</option>
        <option value="addition">addition</option>
        <option value="division">division</option>
        <option value="modulo">modulo</option>
        <option value="multiplication">multiplication</option>
        <option value="subtraction">subtraction</option>
    </select>
    <button type="submit">Calculate</button>
</form>
<c:if test="${requestScope.result != null}">
<p align="center" style="color: floralwhite">Result: ${requestScope.result}</p>
</c:if>
<c:if test="${requestScope.message_inp != null}">
    <p align="center" style="color: floralwhite">${requestScope.message_inp}</p>
</c:if>
<p align="center"><a href="/log">Get log by session</a></p>
<p align="center"><a href="/logOut">Log out</a></p>
</body>
</html>
