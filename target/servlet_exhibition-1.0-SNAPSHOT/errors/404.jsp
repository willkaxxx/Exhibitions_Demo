<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 06.06.2021
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${lang}"/>
    <fmt:setBundle basename="local"/>
    <title><fmt:message key="404.title" /></title>
</head>
<body>
        <h1><fmt:message key="error.text" /></h1>
        <h2><a href="/exhibitions/index?page=1"><fmt:message key="error.goHome" /></a></h2>
</body>
</html>
