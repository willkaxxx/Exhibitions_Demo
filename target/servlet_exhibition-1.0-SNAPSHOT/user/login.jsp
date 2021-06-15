<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 30.05.2021
  Time: 2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title><fmt:message key="login.title"/></title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-xl-5 col-md-8">
            <form class="bg-white  rounded-5 shadow-5-strong p-5"
                  action="${pageContext.request.contextPath}/exhibitions/login" method="get">
                <div class="form-text"><h1><fmt:message key="login.title"/>:</h1></div>
                <div class="form-outline mb-4">
                    <label class="form-label" for="LoginFormEmail"><fmt:message key="login.email"/></label>
                    <input type="email" id="LoginFormEmail" class="form-control" name="email"/>
                    <c:if test="${reg_email_error}">
                        <div class="form-text"><fmt:message key="valid.error.email"/></div>
                    </c:if>
                </div>
                <div class="form-outline mb-4">
                    <label class="form-label" for="LoginFormPassword"><fmt:message key="login.pass"/></label>
                    <input type="password" id="LoginFormPassword" class="form-control" name="pass"/>
                    <c:if test="${reg_pass_error}">
                        <div class="form-text"><fmt:message key="valid.error.pass"/></div>
                    </c:if>
                </div>
                <button type="submit" class="btn btn-primary btn-block"><fmt:message key="login.action"/></button>
                <c:if test="${exist_error}">
                    <div class="form-text"><fmt:message key="login.noUser"/></div>
                </c:if>
            </form>
        </div>
    </div>
</div>
</body>
</html>
