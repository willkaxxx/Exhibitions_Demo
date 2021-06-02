<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 02.06.2021
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Halls:</title>
</head>
<body>
<table border="1">
    <c:forEach items="${exhibitions}" var="product">
        <tr>
            <td><c:out value="${product.id}" /></td>
            <td><c:out value="${product.cost}" /></td>
            <td><c:out value="${product.name}" /></td>
            <td><c:out value="${product.subject}" /></td>
            <td><input type="datetime-local" value="${product.getFormattedBeginning()}" readonly="readonly"></td>
            <td><input type="datetime-local" value="${product.getFormattedEnd()}" readonly="readonly"></td>
            <td><a href="${pageContext.request.contextPath}/exhibitions/admin/editExhibition?exhibitionId=${product.getId()}">Edit</a></td>
        </tr>
    </c:forEach>
</table>
<c:forEach begin="1" end="${totalPages}" var="i" step="1">
    <a href="${pageContext.request.contextPath}/exhibitions/admin/manageExhibitions?page=${i}">${i}</a>
</c:forEach>
<a href="${pageContext.request.contextPath}/exhibitions/admin/editExhibition?exhibitionId=0">Create</a>
</body>
</html>
