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
    <fmt:setLocale value="${lang}"/>
    <fmt:setBundle basename="local"/>
    <title><fmt:message key="common.userHome"/></title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<h1><fmt:message key="user.yourTickets"/>: ${userExhibitions.size()}</h1>
<c:if test="${userExhibitions.size() > 0}">
    <table border="1">
        <tr>
            <td><fmt:message key="entity.exhibition.cost"/></td>
            <td><fmt:message key="entity.exhibition.name"/></td>
            <td><fmt:message key="entity.exhibition.subject"/></td>
            <td><fmt:message key="entity.exhibition.beginning"/></td>
            <td><fmt:message key="entity.exhibition.end"/></td>
        </tr>
        <c:forEach items="${userExhibitions}" var="product">
            <tr>
                <td><c:out value="${product.cost}" /></td>
                <td><c:out value="${product.name}" /></td>
                <td><c:out value="${product.subject}" /></td>
                <td><input type="date" value="${product.getFormattedBeginning()}" readonly="readonly"></td>
                <td><input type="date" value="${product.getFormattedEnd()}" readonly="readonly"></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
