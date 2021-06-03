<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 31.05.2021
  Time: 1:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit current hall</title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
    <form action="${pageContext.request.contextPath}/exhibitions/admin/saveHall" method="post">
        <p>New address</p>
        <input name="address" value="${editHall.address}">
        <c:if test="${editHall.id < 1}"><button type="submit">Create</button></c:if>
        <c:if test="${editHall.id >= 1}"><button type="submit">Save</button></c:if>
    </form>
</body>
</html>
