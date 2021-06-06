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
            <td><input type="date" value="${product.getFormattedBeginning()}" readonly="readonly"></td>
            <td><input type="date" value="${product.getFormattedEnd()}" readonly="readonly"></td>
            <c:if test="${user.isPresent() && user.get().role.ordinal() > 0}">
                <td><a href="${pageContext.request.contextPath}/exhibitions/auth/enroll?exhibitionId=${product.id}">Enroll</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>

<c:forEach begin="1" end="${totalPages}" var="i" step="1">
    <a href="?page=${i}"> ${i} </a>
</c:forEach>
<form>
    <h2>Order by:</h2>
    <select name="orderBy">
        <option value="cost">Cost</option>
        <option value="exhibition_name">Name</option>
        <option value="subject">Subject</option>
        <option value="beginning">Beginning</option>
        <option value="end">End</option>
    </select>
    <select name="dir">
        <option value="asc">Ascending</option>
        <option value="desc">Descending</option>
    </select>
    <h2>Filter by date:</h2>
    <p>Beginning: </p>
    <label>From
        <input name="startBegin" type="date">
    </label>
    <label>To:
        <input name="stopBegin" type="date">
    </label>
    <p>End: </p>
    <label>From:
        <input name="startEnd" type="date">
    </label>
    <label>To:
        <input name="stopEnd" type="date">
    </label>
    <button type="submit">Filter</button>
</form>
</body>
</html>