<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 04.06.2021
  Time: 14:28
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
    <title><fmt:message key="common.userHome"/></title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<h1 class="container"><fmt:message key="user.yourTickets"/>: ${userExhibitions.size()}</h1>
<c:if test="${userExhibitions.size() > 0}">
    <div class="container-fluid">
        <div class="justify-content-center">
            <table class="table table-striped">
                <thead>
                <tr class="table-secondary">
                    <td><fmt:message key="entity.exhibition.cost"/></td>
                    <td><fmt:message key="entity.exhibition.name"/></td>
                    <td><fmt:message key="entity.exhibition.subject"/></td>
                    <td><fmt:message key="entity.exhibition.beginning"/></td>
                    <td><fmt:message key="entity.exhibition.end"/></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userExhibitions}" var="product">
                    <tr>
                        <td><c:out value="${product.cost}" /></td>
                        <td><c:out value="${product.name}" /></td>
                        <td><c:out value="${product.subject}" /></td>
                        <td><c:out value="${product.getFormattedBeginning()}" /></td>
                        <td><c:out value="${product.getFormattedEnd()}" /></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:if>
</body>
</html>
