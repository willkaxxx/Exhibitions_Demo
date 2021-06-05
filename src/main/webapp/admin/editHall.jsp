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
    <fmt:setLocale value="${lang}"/>
    <fmt:setBundle basename="local"/>
    <title><fmt:message key="hall.edit.title"/></title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
    <form action="${pageContext.request.contextPath}/exhibitions/admin/saveHall" method="post">
        <label><fmt:message key="entity.hall.address"/> :
            <input name="address" value="${editHall.address}">
        </label>
        <c:if test="${editHall.id < 1}"><button type="submit"><fmt:message key="common.create"/></button></c:if>
        <c:if test="${editHall.id >= 1}"><button type="submit"><fmt:message key="common.save"/></button></c:if>
    </form>
</body>
</html>
