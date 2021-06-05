
<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 28.05.2021
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <p><fmt:message key="registration.email"/> <input type="email" name="email"> ${reg_email_error}</p>
        <p><fmt:message key="registration.pass"/><input type="text" name="pass"> ${reg_pass_error}</p>
        <p>${exist_error}</p>
        <p><button type="submit"><fmt:message key="registration.action"/></button></p>
    </form>
</body>
</html>
