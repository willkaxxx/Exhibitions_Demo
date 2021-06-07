<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "demo" uri = "/WEB-INF/custom.tld"%>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${lang}"/>
    <fmt:setBundle basename="local"/>
    <title><fmt:message key="common.main.title"/></title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h1><fmt:message key="common.hello"/><demo:Hello user="${user}"/>!</h1>
<table border="1">
    <tr>
        <td><fmt:message key="entity.exhibition.cost"/></td>
        <td><fmt:message key="entity.exhibition.name"/></td>
        <td><fmt:message key="entity.exhibition.subject"/></td>
        <td><fmt:message key="entity.exhibition.beginning"/></td>
        <td><fmt:message key="entity.exhibition.end"/></td>
    </tr>
    <c:forEach items="${exhibitions}" var="product">
        <tr>
            <td><c:out value="${product.cost}" /></td>
            <td><c:out value="${product.name}" /></td>
            <td><c:out value="${product.subject}" /></td>
            <td><input type="date" value="${product.getFormattedBeginning()}" readonly="readonly"></td>
            <td><input type="date" value="${product.getFormattedEnd()}" readonly="readonly"></td>
            <c:if test="${user.isPresent() && user.get().role.ordinal() > 0 && !user.get().hasExhibition(product.id)}">
                <td><a href="${pageContext.request.contextPath}/exhibitions/auth/enroll?exhibitionId=${product.id}"><fmt:message key="common.enroll"/></a></td>
            </c:if>
            <c:if test="${user.isPresent() && user.get().role.ordinal() > 0 && user.get().hasExhibition(product.id)}">
                <td><fmt:message key="common.alreadyEnrolled"/></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<c:forEach begin="1" end="${totalPages}" var="i" step="1">
    <a href="?page=${i}"> ${i} </a>
</c:forEach>
<form>
    <h2><fmt:message key="common.orderBy"/>:</h2>
    <select name="orderBy">
        <option value="cost" ${(sortParams[0] == 'cost') ? 'selected' : 'selected'}><fmt:message key="entity.exhibition.cost"/></option>
        <option value="exhibition_name" ${(sortParams[0] == 'exhibition_name') ? 'selected' : ''}><fmt:message key="entity.exhibition.name"/></option>
        <option value="subject"${(sortParams[0] == 'subject') ? 'selected' : ''}><fmt:message key="entity.exhibition.subject"/></option>
        <option value="beginning"${(sortParams[0] == 'beginning') ? 'selected' : ''}><fmt:message key="entity.exhibition.beginning"/></option>
        <option value="end"${(sortParams[0] == 'end') ? 'selected' : ''}><fmt:message key="entity.exhibition.end"/></option>
    </select>
    <select name="dir">
        <option value="asc" ${(sortParams[1] == 'asc') ? 'selected' : ''}><fmt:message key="common.ascending"/></option>
        <option value="desc" ${(sortParams[1] == 'desc') ? 'selected' : ''}><fmt:message key="common.descending"/></option>
    </select>
    <h2><fmt:message key="common.filterByDate"/>:</h2>
    <p><fmt:message key="entity.exhibition.beginning"/>:</p>
    <label><fmt:message key="common.from"/>:
        <input name="startBegin" type="date" value="${sortParams[2]}">
    </label>
    <label><fmt:message key="common.to"/>:
        <input name="stopBegin" type="date" value="${sortParams[3]}">
    </label>
    <p><fmt:message key="entity.exhibition.end"/>:</p>
    <label><fmt:message key="common.from"/>:
        <input name="startEnd" type="date" value="${sortParams[4]}">
    </label>
    <label><fmt:message key="common.to"/>:
        <input name="stopEnd" type="date" value="${sortParams[5]}">
    </label><br/>
    <button type="submit"><fmt:message key="common.apply"/></button>
</form>
</body>
</html>