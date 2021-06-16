<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 30.05.2021
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
            crossorigin="anonymous"></script>
    <fmt:setLocale value="${lang}"/>
    <fmt:setBundle basename="local"/>
    <title><fmt:message key="adm.hello"/></title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<br>
<br>
<br>
<div class="container-fluid">
    <div class="row">
        <div class="col-6">
            <a class="btn btn-primary btn-lg container-fluid" href="${pageContext.request.contextPath}/exhibitions/admin/manageHalls?page=1"><fmt:message key="adm.home.manageHalls"/></a>
        </div>
        <div class="col-6">
            <a class="btn btn-primary btn-lg container-fluid" href="${pageContext.request.contextPath}/exhibitions/admin/manageExhibitions?page=1"><fmt:message key="adm.home.manageExhibitions"/></a>
        </div>
    </div>
</div>
</body>
</html>
