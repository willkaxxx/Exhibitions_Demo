<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${lang}"/>
    <fmt:setBundle basename="local"/>
    <title>Exhibitions catalog</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<c:if test="${user.isPresent()}">
    <h1>Hello ${user.get().email}!</h1>
</c:if>
<p>${pageContext.request.contextPath.toString()}</p>
<table border="1">
    <tr>
        <td><a href="?orderBy=cost">Cost</a></td>
        <td><a href="?orderBy=exhibition_name">Name</a></td>
        <td><a href="?orderBy=subject">Subject</a></td>
        <td><a href="?orderBy=beginning">Beginning</a></td>
        <td><a href="?orderBy=end">End</a></td>
    </tr>
    <c:forEach items="${exhibitions}" var="product">
        <tr>
            <td><c:out value="${product.cost}" /></td>
            <td><c:out value="${product.name}" /></td>
            <td><c:out value="${product.subject}" /></td>
            <td><input type="date" value="${product.getFormattedBeginning()}" readonly="readonly"></td>
            <td><input type="date" value="${product.getFormattedEnd()}" readonly="readonly"></td>
            <c:if test="${user.isPresent() && user.get().role.ordinal() > 0}">
                <td><a href="${pageContext.request.contextPath}/exhibitions/auth/enroll?exhibitionId=${product.id}">Enroll</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<c:forEach begin="1" end="${totalPages}" var="i" step="1">
    <a href="${pageContext.request.contextPath}?page=${i}">${i}</a>
</c:forEach>
</body>
</html>