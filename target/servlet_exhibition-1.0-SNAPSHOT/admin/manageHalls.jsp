<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 30.05.2021
  Time: 18:59
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
    <title><fmt:message key="hall.title"/></title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
    <table border="1">
        <tr>
            <td><fmt:message key="entity.hall.id"/></td>
            <td><fmt:message key="entity.hall.address"/></td>
        </tr>
        <c:forEach items="${halls}" var="product">
            <tr>
                <td><c:out value="${product.getId()}" /></td>
                <td><c:out value="${product.getAddress()}" /></td>
                <td><a href="${pageContext.request.contextPath}/exhibitions/admin/editHall?hallId=${product.getId()}"><fmt:message key="common.edit"/></a></td>
            </tr>
        </c:forEach>
    </table>
    <c:forEach begin="1" end="${totalPages}" var="i" step="1">
        <a href="${pageContext.request.contextPath}/exhibitions/admin/manageHalls?page=${i}">${i}</a>
    </c:forEach>
    <p><a href="${pageContext.request.contextPath}/exhibitions/admin/editHall?hallId=0"><fmt:message key="common.create"/></a></p>
</body>
</html>
