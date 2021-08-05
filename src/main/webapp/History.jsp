<%--Created by IntelliJ IDEA.
User: A E S T H E T I C
Date: 30.07.2021
Time: 12:23
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>History</title>
</head>
<body background="https://remetc.ru/images/877940.jpg">
<h1 align="center" style="color: floralwhite">Session history</h1>
<form align="center" action="/log" method="post">
    <select name="type" size="?" required>
        <option disabled>Select type operation</option>
        <option value="addition">addition</option>
        <option value="division">division</option>
        <option value="modulo">modulo</option>
        <option value="multiplication">multiplication</option>
        <option value="subtraction">subtraction</option>
    </select>
    <button type="submit">Get log by operation</button>
</form>
<p align="center"><button onclick='location.href="/mathOperation"'>Back to calculator</button></p>
<table summary="Log list." align="center" bgcolor="#fff8dc" border="3" cellpadding="3">
    <tr>
        <th>Number 1</th>
        <th>Number 2</th>
        <th>Operation</th>
        <th>Name user</th>
        <th>Result</th>
    </tr>
    <c:forEach var="math_operation" items="${requestScope.log_list}">
        <tr>
            <td>${math_operation.numOne}</td>
            <td>${math_operation.numTwo}</td>
            <td>${math_operation.typeOp}</td>
            <td>${math_operation.user.name}</td>
            <td>${math_operation.result}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
