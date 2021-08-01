<%--
  Created by IntelliJ IDEA.
  User: A E S T H E T I C
  Date: 28.07.2021
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
<h1>Calculator</h1>
<form action="/mathOperation" method="post">
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
    <button>Calculate</button>
</form>
<p>
    <output name="Result">${requestScope.result}</output>
</p>
<p>${requestScope.message_inp}</p>
<form action="/log">
    <button>Get history</button>
</form>
<form action="/logOut">
    <button>Exit session</button>
</form>
</body>
</html>
