<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 30.05.2021
  Time: 2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${lang}"/>
    <fmt:setBundle basename="local"/>
    <title><fmt:message key="login.title"/></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/exhibitions/login" method="get">
    <p><fmt:message key="login.email"/> <input type="email" name="email"> ${email_error}</p>
    <p><fmt:message key="login.pass"/><input type="password" name="pass"> ${pass_error}</p>
    <p>${exist_error}</p>
    <p><button type="submit"><fmt:message key="login.action"/></button></p>
</form>
</body>
</html>
