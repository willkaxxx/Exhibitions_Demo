
<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 28.05.2021
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${lang}"/>
    <fmt:setBundle basename="local"/>
    <title><fmt:message key="registration.title"/></title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/exhibitions/registration" method="post">
        <p><fmt:message key="registration.email"/> <input type="text" name="email">
            <c:if test="${reg_email_error}"><fmt:message key="valid.error.email"/></c:if></p>
        <p><fmt:message key="registration.pass"/><input type="password" name="pass">
            <c:if test="${reg_pass_error}"><fmt:message key="valid.error.pass"/></c:if></p>
        <c:if test="${exist_error}"><fmt:message key="registration.userExists"/></c:if>
        <p><button type="submit"><fmt:message key="registration.action"/></button></p>
    </form>
</body>
</html>
