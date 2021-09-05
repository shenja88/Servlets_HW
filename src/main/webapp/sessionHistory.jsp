<%--
  Created by IntelliJ IDEA.
  User: A E S T H E T I C
  Date: 24.08.2021
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>History</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-sm-6">
            <form action="/log" method="post">
                <fieldset>
                    <div class="mb-3">
                        <select name="type" class="form-select form-select-sm-4"
                                aria-label=".form-select-sm example">
                            <option disabled>Select operation</option>
                            <option value="addition">Addition</option>
                            <option value="division">Division</option>
                            <option value="modulo">Modulo</option>
                            <option value="multiplication">Multiplication</option>
                            <option value="subtraction">Subtraction</option>
                        </select>
                        <div class="d-flex justify-content-end">
                            <button type="submit" class="btn btn-primary m-3">Get operations</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-sm-6">
            <div>
                <ol class="list-group list-group m-3">
                    <c:forEach var="math_operation" items="${requestScope.currentList}">
                        <li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold">Operation: ${math_operation.typeOp}.</div>
                                Num: ${math_operation.numOne}.
                                Num: ${math_operation.numTwo}.
                                Name: ${math_operation.user.name}.
                            </div>
                            <span class="badge bg-primary rounded-pill">Result: ${math_operation.result}.</span>
                        </li>
                    </c:forEach>
                </ol>
            </div>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-sm-6">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <c:if test="${requestScope.currentPage != 1}">
                            <a class="page-link" href="/log?currentPage=${requestScope.currentPage - 1}">Previous</a>
                        </c:if>
                    </li>
                    <c:forEach begin="1" end="${requestScope.numPages}" var="i">
                    <c:choose>
                    <c:when test="${requestScope.currentPage eq i}">
                        <li class="page-item disabled">
                            <a class="page-link">${i}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                    <li class="page-item"><a href="/log?currentPage=${i}"
                                             class="page-link">${i}</a>
                        </c:otherwise>
                        </c:choose>
                        </c:forEach>
                    </li>
                    <li class="page-item">
                        <c:if test="${requestScope.currentPage lt requestScope.numPages}">
                    <li class="page-item">
                        <a href="/log?currentPage=${currentPage + 1}" class="page-link">Next</a>
                    </li>
                    </c:if>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>
