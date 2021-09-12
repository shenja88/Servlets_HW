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
    <title>Update telephone</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-sm-4 m5 bg-success p-2 text-dark bg-opacity-10 rounded">
            <form action="/updateTel" method="post">
                <div class="mb-3">
                    <div class="mb-3">
                        <label for="exampleInputTel" class="form-label">Telephone</label>
                        <input type="number" name="telephone" class="form-control" id="exampleInputTel">
                    </div>
                    <button type="submit" class="btn btn-primary">Update telephone</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>