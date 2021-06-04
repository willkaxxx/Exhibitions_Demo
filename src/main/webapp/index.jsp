<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<c:if test="${user != null}">
    <h1>Hello!</h1>
</c:if>
<table border="1">
    <tr>
        <td>Cost</td>
        <td>Name</td>
        <td>Subject</td>
        <td>Beginning</td>
        <td>End</td>
    </tr>
    <c:forEach items="${exhibitions}" var="product">
        <tr>
            <td><c:out value="${product.cost}" /></td>
            <td><c:out value="${product.name}" /></td>
            <td><c:out value="${product.subject}" /></td>
            <td><input type="datetime-local" value="${product.getFormattedBeginning()}" readonly="readonly"></td>
            <td><input type="datetime-local" value="${product.getFormattedEnd()}" readonly="readonly"></td>
            <c:if test="${user != null && user.get().role.ordinal() > 0}">
                <td><a href="${pageContext.request.contextPath}/exhibitions/auth/enroll?exhibitionId=${product.id}">Enroll</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<c:forEach begin="1" end="${totalPages}" var="i" step="1">
    <a href="${pageContext.request.contextPath}/exhibitions/index?page=${i}">${i}</a>
</c:forEach>
</body>
</html>