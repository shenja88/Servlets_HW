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
<body>
<jsp:include page="_header.jsp"/>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-sm-4 m5 bg-success p-2 text-dark bg-opacity-10 rounded">
            <form action="/mathOperation" method="post">
                <fieldset>
                        <div class="m-1">
                            <label for="inputNum1" class="col-sm col-form-label">Number one</label>
                            <input required type="number" step="0.0000000000001" name="num" class="form-control" id="inputNum1">
                        </div>
                        <div class="m-1">
                            <label for="inputNum2" class="col-sm col-form-label">Number two</label>
                            <input required type="number" step="0.000000000001" name="num" class="form-control" id="inputNum2">
                        </div>
                        <div class="m-1">
                            <label for="selectOp" class="col-sm col-form-label">Type operation</label>
                            <select name="operation" class="form-select form-select-sm-3"
                                    aria-label=".form-select-sm example" id="selectOp">
                                <option disabled>Select operation</option>
                                <option value="addition">Addition</option>
                                <option value="division">Division</option>
                                <option value="modulo">Modulo</option>
                                <option value="multiplication">Multiplication</option>
                                <option value="subtraction">Subtraction</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary m-2">Calculate</button>
                </fieldset>
            </form>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-sm-5">
            <c:if test="${requestScope.result != null}">
                <div class="alert alert-info m-3" role="alert">
                    Result: ${requestScope.result}
                </div>
            </c:if>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-sm-5">
            <c:if test="${requestScope.message_inp != null}">
                <div class="alert alert-info m-3" role="alert">
                        ${requestScope.message_inp}
                </div>
            </c:if>
        </div>
    </div>
</div>
