<%--
  Created by IntelliJ IDEA.
  User: A E S T H E T I C
  Date: 16.08.2021
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
</head>
<body>
<div class="container fw-bolder">
    <div class="row mb-5 mt-2">
        <div class="d-flex flex-wrap justify-content-lg-start bg-success p-2 text-dark bg-opacity-50 rounded">
            <a href="/main" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                <svg xmlns="http://www.w3.org/2000/svg" width="45" height="45" fill="currentColor"
                     class="bi bi-calculator-fill" viewBox="0 0 16 16">
                    <path d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2zm2 .5v2a.5.5 0 0 0 .5.5h7a.5.5 0 0 0 .5-.5v-2a.5.5 0 0 0-.5-.5h-7a.5.5 0 0 0-.5.5zm0 4v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5zM4.5 9a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zM4 12.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5zM7.5 6a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zM7 9.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5zm.5 2.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zM10 6.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5zm.5 2.5a.5.5 0 0 0-.5.5v4a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-4a.5.5 0 0 0-.5-.5h-1z"></path>
                </svg>
            </a>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <c:if test="${sessionScope.user == null}">
                    <li><a href="/registration" class="nav-link px-2 link-dark border border-3 border-dark rounded me-1 bg-success p-2 bg-opacity-75">Registration</a></li>
                    <li><a href="/login" class="nav-link px-2 link-dark border border-3 border-dark rounded me-1 bg-success p-2 bg-opacity-75">Log in</a></li>
                    <li class="nav-item"><a class="nav-link disabled text-dark" href="#" tabindex="-1" aria-disabled="true">Hello
                        guest!</a></li>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <li><a href="/mathOperation" class="nav-link px-2 link-dark border border-3 border-dark rounded me-1 bg-success p-2 bg-opacity-75">Calculator</a></li>
                    <li><a href="/log" class="nav-link px-2 link-dark border border-3 border-dark rounded me-1 bg-success p-2 bg-opacity-75">History</a></li>
                    <li class="nav-item"><a class="nav-link disabled text-dark" href="#" tabindex="-1" aria-disabled="true">Hello
                    ${sessionScope.user.name}!</a></li>
                </c:if>
            </ul>
            <c:if test="${sessionScope.user != null}">
                <div class="dropdown text-end me-5">
                    <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="https://img1.freepng.ru/20180509/iqq/kisspng-person-logo-computer-icons-5af2c2023a8e89.0321517415258588182399.jpg"
                             alt="mdo" width="45" height="45" class="rounded">
                    </a>
                    <ul class="dropdown-menu text-big" aria-labelledby="dropdownUser1" style="">
                        <li><a href="/editPassword" class="nav-link px-2 link-dark">Edit password</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a href="/editName" class="nav-link px-2 link-dark">Edit name</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a href="/logOut" class="nav-link px-2 link-dark">Log out</a></li>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous"></script>
</body>
</html>
