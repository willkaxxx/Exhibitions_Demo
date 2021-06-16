<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="demo" uri="/WEB-INF/custom.tld" %>
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
    <title><fmt:message key="common.main.title"/></title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<br>
<h1 class="text-center text-muted"><fmt:message key="common.hello"/><demo:Hello user="${user}"/>!</h1>
<br>
<br>
<div class="container-fluid">
    <div class="row">
        <div class="col-3">
            <div class="p-3 border bg-light">
                <form>
                <h2 class="text-muted text-center"><fmt:message key="common.orderBy"/>:</h2>
                    <div class="row">
                        <div class="col-6">
                            <select name="orderBy" class="form-select">
                                <option value="cost" ${(sortParams[0] == 'cost') ? 'selected' : 'selected'}><fmt:message
                                        key="entity.exhibition.cost"/></option>
                                <option value="exhibition_name" ${(sortParams[0] == 'exhibition_name') ? 'selected' : ''}><fmt:message
                                        key="entity.exhibition.name"/></option>
                                <option value="subject"${(sortParams[0] == 'subject') ? 'selected' : ''}><fmt:message
                                        key="entity.exhibition.subject"/></option>
                                <option value="beginning"${(sortParams[0] == 'beginning') ? 'selected' : ''}><fmt:message
                                        key="entity.exhibition.beginning"/></option>
                                <option value="end"${(sortParams[0] == 'end') ? 'selected' : ''}><fmt:message
                                        key="entity.exhibition.end"/></option>
                            </select>
                        </div>
                        <div class="col-6">
                            <select name="dir" class="form-select">
                                <option value="asc" ${(sortParams[1] == 'asc') ? 'selected' : ''}><fmt:message key="common.ascending"/></option>
                                <option value="desc" ${(sortParams[1] == 'desc') ? 'selected' : ''}><fmt:message
                                        key="common.descending"/></option>
                            </select>
                        </div>
                    </div>
                    <hr/>
                    <h2 class="text-muted text-center"><fmt:message key="common.filterByDate"/>:</h2>
                    <div class="container-fluid">
                        <h5><fmt:message key="entity.exhibition.beginning"/>:</h5>
                        <div class="input-group mb-3">
                            <span class="input-group-text" ><fmt:message key="common.from"/>:</span>
                            <input name="startBegin" class="form-control" type="date" value="${sortParams[2]}">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" ><fmt:message key="common.to"/>:</span>
                            <input name="stopBegin" class="form-control" type="date" value="${sortParams[3]}">
                        </div>
                    </div>
                    <hr/>
                    <div class="container-fluid">
                        <h5><fmt:message key="entity.exhibition.end"/>:</h5>
                        <div class="input-group mb-3">
                            <span class="input-group-text" ><fmt:message key="common.from"/>:</span>
                            <input name="startEnd" class="form-control" type="date" value="${sortParams[4]}">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" ><fmt:message key="common.to"/>:</span>
                            <input name="stopEnd" class="form-control" type="date" value="${sortParams[5]}">
                        </div>
                    </div>
                    <hr/>
                    <div class="row">
                        <div class="col-6">
                            <button  class="btn btn-primary btn-block container-fluid" type="submit"><fmt:message key="common.apply"/></button>
                        </div>
                        <div class="col-6">
                            <a  class="btn btn-outline-primary btn-block container-fluid" href="?orderBy=cost&dir=asc&startBegin=&stopBegin=&startEnd=&stopEnd="><fmt:message key="common.clear"/></a>
                        </div>
                    </div>
            </form>
            </div>
        </div>
        <div class="col-9">
            <div class="row">
                <c:forEach items="${exhibitions}" var="product">
                    <div class="col-4 text-center">
                        <div class="p-3 border border-dark bg-light">
                            <h5 class="text-wrap"><fmt:message key="entity.exhibition.name"/>: <c:out value="${product.name}"/></h5>
                            <h6 class="text-muted"><fmt:message key="entity.exhibition.subject"/>: <c:out value="${product.subject}"/></h6>
                            <h6 class="text-muted"><fmt:message key="entity.exhibition.beginning"/>: <c:out value="${product.getFormattedBeginning()}"/></h6>
                            <h6 class="text-muted"><fmt:message key="entity.exhibition.end"/>: <c:out value="${product.getFormattedEnd()}"/></h6>
                            <h6 class="text-muted"><fmt:message key="entity.exhibition.cost"/>: <c:out value="${product.cost}"/></h6>
<%--                            <p><c:out value="${product.name}"/></p>--%>
<%--                            <p><c:out value="${product.subject}"/></p>--%>
<%--                            <p><c:out value="${product.getFormattedBeginning()}"/></p>--%>
<%--                            <p><c:out value="${product.getFormattedEnd()}"/></p>--%>
                            <c:if test="${user.isPresent() && user.get().role.ordinal() > 0}">
                                <c:if test="${!user.get().hasExhibition(product.id)}">
                                    <a class="btn btn-primary btn-block container-fluid" href="${pageContext.request.contextPath}/exhibitions/auth/enroll?exhibitionId=${product.id}">
                                        <fmt:message key="common.enroll"/></a>
                                </c:if>
                                <c:if test="${user.get().hasExhibition(product.id)}">
                                    <span class="btn btn-outline-primary btn-block container-fluid"><fmt:message key="common.alreadyEnrolled"/></span>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <nav aria-label="Page navigation">
                <ul class="pagination pagination-sm justify-content-center">
                    <c:forEach begin="1" end="${totalPages}" var="i" step="1">
                        <li class="page-item"><a class="page-link" href="?page=${i}"> ${i} </a></li>
                    </c:forEach>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>