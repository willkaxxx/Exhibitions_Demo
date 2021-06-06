<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 03.06.2021
  Time: 2:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<header>
    <fmt:setLocale value="${lang}"/>
    <fmt:setBundle basename="local"/>
    <c:if test="${user.isPresent()}">
        <a href="/exhibitions/user/logout"><fmt:message key="common.logout" /></a>
        <c:if test="${user.get().role.ordinal() == 2}">
            <a href="${pageContext.request.contextPath}exhibitions/redirect:/admin/adminHome.jsp"><fmt:message key="common.adminHome" /></a><br/>
        </c:if>
        <c:if test="${user.get().role.ordinal() > 0}">
            <a href="/exhibitions/auth/userHome"><fmt:message key="common.userHome" /></a><br/>
        </c:if>
    </c:if>
    <c:if test="${!user.isPresent()}">
        <a href="/exhibitions/redirect:/user/login.jsp"><fmt:message key="common.login" /></a>
        <a href="/exhibitions/redirect:/user/registration.jsp"><fmt:message key="common.registration" /></a>
    </c:if>
    <a href="/exhibitions/index"><fmt:message key="home.go" /></a>
    <a href="?sessionLocale=en"><fmt:message key="lang.en"/></a>
    <a href="?sessionLocale=ua"><fmt:message key="lang.ua"/></a>
    <hr>
</header>

