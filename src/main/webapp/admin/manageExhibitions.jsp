<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 02.06.2021
  Time: 14:49
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
    <title><fmt:message key="exhib.title"/></title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<table border="1">
    <tr>
        <td><fmt:message key="entity.exhibition.id"/></td>
        <td><fmt:message key="entity.exhibition.cost"/></td>
        <td><fmt:message key="entity.exhibition.name"/></td>
        <td><fmt:message key="entity.exhibition.subject"/></td>
        <td><fmt:message key="entity.exhibition.beginning"/></td>
        <td><fmt:message key="entity.exhibition.end"/></td>
    </tr>
    <c:forEach items="${exhibitions}" var="product">
        <tr>
            <td><c:out value="${product.id}" /></td>
            <td><c:out value="${product.cost}" /></td>
            <td><c:out value="${product.name}" /></td>
            <td><c:out value="${product.subject}" /></td>
            <td><input type="date" value="${product.getFormattedBeginning()}" readonly="readonly"></td>
            <td><input type="date" value="${product.getFormattedEnd()}" readonly="readonly"></td>
            <td><a href="${pageContext.request.contextPath}/exhibitions/admin/editExhibition?exhibitionId=${product.getId()}">
                <fmt:message key="common.edit"/>
            </a></td>
        </tr>
    </c:forEach>
</table>
<c:forEach begin="1" end="${totalPages}" var="i" step="1">
    <a href="${pageContext.request.contextPath}/exhibitions/admin/manageExhibitions?page=${i}">${i}</a>
</c:forEach>
<a href="${pageContext.request.contextPath}/exhibitions/admin/editExhibition?exhibitionId=0"><fmt:message key="common.create"/></a>
</body>
</html>
