<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 02.06.2021
  Time: 15:22
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
    <title><fmt:message key="exhib.edit.title"/></title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<form action="${pageContext.request.contextPath}/exhibitions/admin/saveExhibition" id="act" method="post">
    <label><fmt:message key="entity.exhibition.name"/> :
        <input name="name" value="${editExhibition.name}" type="text">
    </label><br>
    <label><fmt:message key="entity.exhibition.subject"/> :
        <input name="subject" value="${editExhibition.subject}" type="text">
    </label><br>
    <label><fmt:message key="entity.exhibition.cost"/> :
        <input name="cost" value="${editExhibition.cost}" type="number" min="0.00" max="10000.00" step="0.01">
    </label><br>
    <label><fmt:message key="entity.exhibition.beginning"/> :
        <input name="beginning" value="${editExhibition.getFormattedBeginning()}" type="date">
    </label><br>
    <label><fmt:message key="entity.exhibition.end"/> :
        <input name="end" value="${editExhibition.getFormattedEnd()}" type="date">
    </label><br>
</form>
<p>
    <c:if test="${editExhibition.id < 1}">
        <button type="submit" form="act"><fmt:message key="common.create"/></button>
    </c:if>
    <c:if test="${editExhibition.id >= 1}">
        <button type="submit" form="act"><fmt:message key="common.save"/></button>
        <button type="submit" form="act" formaction="${pageContext.request.contextPath}/exhibitions/admin/deleteExhibition"><fmt:message key="common.delete"/></button>
    </c:if>
</p>
<c:if test="${editExhibition.halls.size() > 0}">
<table border="1">
    <c:forEach items="${editExhibition.halls}" var="product">
        <tr>
            <td><c:out value="${product.id}" /></td>
            <td><c:out value="${product.address}" /></td>
            <td><a href="/exhibitions/admin/deleteHallFromExhibition?Hid=${product.id}&Eid=${editExhibition.id}"><fmt:message key="common.delete"/></a></td>
        </tr>
    </c:forEach>
</c:if>
</table>
<c:if test="${editExhibition.id > 0}">
    <form action="/exhibitions/admin/addHallToExhibition?Hid=${hallToAdd}&Eid=${editExhibition.id}" method="post">
        <select name="hallToAdd">
            <c:forEach items="${allHalls}" var="item">
                <option value="${item.id}">${item.address}</option>
            </c:forEach>
        </select>
        <button type="submit"><fmt:message key="common.add"/></button>
        <a href="/exhibitions/admin/addHallToExhibition?Eid=${editExhibition.id}"></a>
    </form>
</c:if>
</body>
</html>
