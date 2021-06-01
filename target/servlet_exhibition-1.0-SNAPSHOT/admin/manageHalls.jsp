<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 30.05.2021
  Time: 18:59
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
        <c:forEach items="${halls}" var="product">
            <tr>
                <td><c:out value="${product.getId()}" /></td>
                <td><c:out value="${product.getAddress()}" /></td>
                <td><a href="${pageContext.request.contextPath}/exhibitions/admin/editHall?hallId=${product.getId()}">Edit</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
