<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 31.05.2021
  Time: 1:46
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
    <title><fmt:message key="hall.edit.title"/></title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
    <form action="${pageContext.request.contextPath}/exhibitions/admin/saveHall" method="post">
        <label><fmt:message key="entity.hall.address"/> :
            <input name="address" value="${editHall.address}">
            <c:if test="${nAddress_error}"><fmt:message key="valid.error.text"/></c:if>
        </label><br/>
        <c:if test="${editHall.id < 1}"><button type="submit"><fmt:message key="common.create"/></button></c:if>
        <c:if test="${editHall.id >= 1}"><button type="submit"><fmt:message key="common.save"/></button></c:if>
    </form>
</body>
</html>
