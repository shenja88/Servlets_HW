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
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1 align="center">Session history</h1>
<form align="center" action="/log" method="post">
    <label for="type">Type operation</label>
        <select id="type" name="type" size="?" required>
            <option disabled>Select type operation</option>
            <option value="addition">addition</option>
            <option value="division">division</option>
            <option value="modulo">modulo</option>
            <option value="multiplication">multiplication</option>
            <option value="subtraction">subtraction</option>
        </select>
    <button class="button_submit" type="submit">Get log by operation</button>
</form>
<a class="my_href" href="/mathOperation">Back to calculator</a>
<p>Operations</p>
<div>
<c:forEach var="math_operation" items="${requestScope.log_list}">
    <ul>
        <li>Num: ${math_operation.numOne}.
            Num: ${math_operation.numTwo}.
            Operation: ${math_operation.typeOp}.
            Result: ${math_operation.result}.
            Name: ${math_operation.user.name}.
        </li>
    </ul>
</c:forEach>
</div>
</body>
</html>
