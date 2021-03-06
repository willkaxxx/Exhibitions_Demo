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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
            crossorigin="anonymous"></script>
    <fmt:setLocale value="${lang}"/>
    <fmt:setBundle basename="local"/>
    <title><fmt:message key="exhib.edit.title"/></title>
</head>
<body>
<br/>
<br/>
<br/>
<div class="row">
    <div class="col-3">
        <c:if test="${editExhibition.id > 0}">
            <div class="container bg-light rounded rounded-5 shadow-5-strong p-5">
                <div class="justify-content-center">
                    <h3 class="text-muted"><fmt:message key="hall.title"/></h3>
                    <c:if test="${editExhibition.halls.size() > 0}">
                        <table class="table table-striped">
                            <c:forEach items="${editExhibition.halls}" var="product">
                                <tr>
                                    <td><c:out value="${product.address}"/></td>
                                    <td><a class="btn btn-outline-primary btn-block"
                                           href="${pageContext.request.contextPath}/exhibitions/admin/deleteHallFromExhibition?Hid=${product.id}&Eid=${editExhibition.id}"><fmt:message
                                            key="common.delete"/></a></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <hr/>
                    </c:if>
                    <form action="${pageContext.request.contextPath}/exhibitions/admin/addHallToExhibition?Hid=${hallToAdd}&Eid=${editExhibition.id}"
                          method="post">
                        <div class="input-group mb-3">
                            <select class="form-select" name="hallToAdd">
                                <c:forEach items="${allHalls}" var="item">
                                    <option value="${item.id}">${item.address}</option>
                                </c:forEach>
                            </select>
                            <button class="btn btn-primary btn-block input-group-text" type="submit"><fmt:message
                                    key="common.add"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
    </div>
    <div class="col-6">
        <div class="container justify-content-center">
            <form class="bg-light rounded rounded-5 shadow-5-strong p-5"
                  action="${pageContext.request.contextPath}/exhibitions/admin/saveExhibition" id="act" method="post">
                <div class="input-group mb-3">
                    <span class="input-group-text"><fmt:message key="entity.exhibition.name"/>:</span>
                    <input class="form-control" name="name" value="${editExhibition.name}" type="text">
                    <c:if test="${nName_error}"><fmt:message key="valid.error.text"/></c:if>
                </div>
                <div class="input-group mb-3">
                    <span class="input-group-text"><fmt:message key="entity.exhibition.subject"/>:</span>
                    <input class="form-control" name="subject" value="${editExhibition.subject}" type="text">
                    <c:if test="${nSubject_error}"><fmt:message key="valid.error.text"/></c:if>
                </div>
                <div class="input-group mb-3">
                    <span class="input-group-text"><fmt:message key="entity.exhibition.cost"/>:</span>
                    <input class="form-control" name="cost" value="${editExhibition.cost}" type="number" min="0.00"
                           max="10000.00" step="0.01">
                    <c:if test="${nCost_error}"><fmt:message key="valid.error.decimal"/></c:if>
                </div>
                <div class="input-group mb-3">
                    <span class="input-group-text"><fmt:message key="entity.exhibition.beginning"/>:</span>
                    <input class="form-control" name="beginning" value="${editExhibition.getFormattedBeginning()}"
                           type="date">
                    <c:if test="${nDate_error}"><fmt:message key="valid.error.beginning"/></c:if>
                </div>
                <div class="input-group mb-3">
                    <span class="input-group-text"><fmt:message key="entity.exhibition.end"/>:</span>
                    <input class="form-control" name="end" value="${editExhibition.getFormattedEnd()}" type="date">
                </div>
                <c:if test="${editExhibition.id < 1}">
                    <button class="btn btn-primary btn-block" type="submit" form="act"><fmt:message
                            key="common.create"/></button>
                </c:if>
                <c:if test="${editExhibition.id >= 1}">
                    <button class="btn btn-primary btn-block" type="submit" form="act"><fmt:message
                            key="common.save"/></button>
                    <button class="btn btn-danger btn-block" ${editExhibition.users.size() > 0 ? "disabled" : ""}
                            type="submit" form="act"
                            formaction="${pageContext.request.contextPath}/exhibitions/admin/deleteExhibition">
                        <fmt:message key="common.delete"/></button>
                </c:if>
                <a class="btn btn-primary btn-block"
                   href="${pageContext.request.contextPath}/exhibitions/admin/manageExhibitions?page=1">
                    <fmt:message key="common.cancel"/>
                </a>
            </form>
        </div>
    </div>
    <div class="col-3">
        <c:if test="${editExhibition.users.size() > 0}">
            <div class="container bg-light rounded rounded-5 shadow-5-strong p-5">
                <div class="justify-content-center">
                    <h3 class="text-muted"><fmt:message key="entity.userTable"/>: ${editExhibition.users.size()}</h3>
                    <table class="table table-striped">
                        <tr class="table-secondary">
                            <td><fmt:message key="entity.user.id"/></td>
                            <td><fmt:message key="entity.user.email"/></td>
                        </tr>
                        <c:forEach items="${editExhibition.users}" var="user">
                            <tr>
                                <td><c:out value="${user.id}"/></td>
                                <td><c:out value="${user.email}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </c:if>
    </div>
</div>


<%--<form action="${pageContext.request.contextPath}/exhibitions/admin/saveExhibition" id="act" method="post"></form>--%>
<%--    <label><fmt:message key="entity.exhibition.name"/> :--%>
<%--        <input name="name" value="${editExhibition.name}" type="text">--%>
<%--        <c:if test="${nName_error}"><fmt:message key="valid.error.text"/></c:if>--%>
<%--    </label><br>--%>
<%--    <label><fmt:message key="entity.exhibition.subject"/> :--%>
<%--        <input name="subject" value="${editExhibition.subject}" type="text">--%>
<%--        <c:if test="${nSubject_error}"><fmt:message key="valid.error.text"/></c:if>--%>
<%--    </label><br>--%>
<%--    <label><fmt:message key="entity.exhibition.cost"/> :--%>
<%--        <input name="cost" value="${editExhibition.cost}" type="number" min="0.00" max="10000.00" step="0.01">--%>
<%--        <c:if test="${nCost_error}"><fmt:message key="valid.error.decimal"/></c:if>--%>
<%--    </label><br>--%>
<%--    <label><fmt:message key="entity.exhibition.beginning"/> :--%>
<%--        <input name="beginning" value="${editExhibition.getFormattedBeginning()}" type="date">--%>
<%--        <c:if test="${nDate_error}"><fmt:message key="valid.error.beginning"/></c:if>--%>
<%--    </label><br>--%>
<%--    <label><fmt:message key="entity.exhibition.end"/> :--%>
<%--        <input name="end" value="${editExhibition.getFormattedEnd()}" type="date">--%>
<%--    </label><br>--%>
<%--</form>--%>
<%--<c:if test="${editExhibition.halls.size() > 0}">--%>
<%--<table border="1">--%>
<%--    <c:forEach items="${editExhibition.halls}" var="product">--%>
<%--        <tr>--%>
<%--            <td><c:out value="${product.id}" /></td>--%>
<%--            <td><c:out value="${product.address}" /></td>--%>
<%--            <td><a href="/exhibitions/admin/deleteHallFromExhibition?Hid=${product.id}&Eid=${editExhibition.id}"><fmt:message key="common.delete"/></a></td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>
<%--</c:if>--%>
<%--<c:if test="${editExhibition.id > 0}">--%>
<%--    <form action="/exhibitions/admin/addHallToExhibition?Hid=${hallToAdd}&Eid=${editExhibition.id}" method="post">--%>
<%--        <select name="hallToAdd">--%>
<%--            <c:forEach items="${allHalls}" var="item">--%>
<%--                <option value="${item.id}">${item.address}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--        <button type="submit"><fmt:message key="common.add"/></button>--%>
<%--        <a href="/exhibitions/admin/addHallToExhibition?Eid=${editExhibition.id}"></a>--%>
<%--    </form>--%>
<%--</c:if>--%>
<%--<c:if test="${editExhibition.users.size() > 0}">--%>
<%--    <h2><fmt:message key="entity.userTable"/>: ${editExhibition.users.size()}</h2><br/>--%>
<%--    <table border="1">--%>
<%--        <tr>--%>
<%--            <td><fmt:message key="entity.user.id"/></td>--%>
<%--            <td><fmt:message key="entity.user.email"/></td>--%>
<%--        </tr>--%>
<%--        <c:forEach items="${editExhibition.users}" var="user">--%>
<%--            <tr>--%>
<%--                <td><c:out value="${user.id}" /></td>--%>
<%--                <td><c:out value="${user.email}" /></td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--    </table>--%>
<%--</c:if>--%>
</body>
</html>
