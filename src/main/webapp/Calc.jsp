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
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1 align="center">Calculator</h1>
<div>
    <form align="center" action="/mathOperation" method="post">
        <label for="num1">Number one</label>
        <input id="num1" type="number" step="0.00001" name="num">
        <label for="num2">Number two</label>
        <input id="num2" type="number" step="0.00001" name="num">
        <label for="oper">Operation</label>
        <select id="oper" required name="operation" size="?">
            <option disabled>Select operation</option>
            <option value="addition">addition</option>
            <option value="division">division</option>
            <option value="modulo">modulo</option>
            <option value="multiplication">multiplication</option>
            <option value="subtraction">subtraction</option>
        </select>
        <button class="button_submit" type="submit">Calculate</button>
    </form>
    <c:if test="${requestScope.result != null}">
        <p>Result: ${requestScope.result}</p>
    </c:if>
    <c:if test="${requestScope.message_inp != null}">
        <p>${requestScope.message_inp}</p>
    </c:if>
    <div class="my_div">
        <a class="my_href" href="/log">Get log by session</a>
        <a class="my_href" href="/logOut">Log out</a>
    </div>
</div>
</body>
</html>
