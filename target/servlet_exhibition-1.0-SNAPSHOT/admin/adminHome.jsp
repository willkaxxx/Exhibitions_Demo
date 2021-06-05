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
    <fmt:setLocale value="${lang}"/>
    <fmt:setBundle basename="local"/>
    <title><fmt:message key="adm.hello"/></title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<a href="${pageContext.request.contextPath}/exhibitions/admin/manageHalls?page=1"><fmt:message key="adm.home.manageHalls"/></a><br/>
<a href="${pageContext.request.contextPath}/exhibitions/admin/manageExhibitions?page=1"><fmt:message key="adm.home.manageExhibitions"/></a><br/>
</body>
</html>
