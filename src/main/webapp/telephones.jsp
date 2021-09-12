<%--
  Created by IntelliJ IDEA.
  User: A E S T H E T I C
  Date: 08.09.2021
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Telephones</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-sm-6 m3 b1 rounded">
            <ol class="list-group list-group-numbered">
                <c:forEach var="telephones" items="${requestScope.telephones_list}">
                    <li class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                            <c:if test ="${telephones.primary == true}">
                                <div class="fw-bold text-decoration-underline">Primary telephone</div>
                            </c:if>
                            <div class="fw-bold">${telephones.number}</div>
                        </div>
                        <span class="badge bg-primary rounded-pill"><a class="page-link"
                                                                       href="/updateTel?id=${telephones.id}">Update</a></span>
                        <span class="badge bg-primary rounded-pill"><a class="page-link"
                                                                       href="/removeTel?id=${telephones.id}">Remove</a></span>
                    </li>
                </c:forEach>
            </ol>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-sm-2 m3 b1 rounded">
            <a href="/saveTelephone"
               class="btn btn-primary m-2">Add new Telephone</a>
        </div>
    </div>
    <c:if test="${requestScope.message_tel != null}">
        <div class="row justify-content-center">
            <div class="col-sm-4">
                <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
                    <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"></path>
                    </symbol>
                    <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"></path>
                    </symbol>
                    <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"></path>
                    </symbol>
                </svg>
                <div class="alert alert-primary d-flex align-items-center" role="alert">
                    <svg class="bi flex-shrink-0 m-3" width="24" height="24" role="img" aria-label="Info:">
                        <use href="#info-fill"></use>
                    </svg>
                    <div>
                            ${requestScope.message_tel}
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>
</div>
</body>
</html>