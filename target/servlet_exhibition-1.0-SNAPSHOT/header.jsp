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
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/exhibitions/index"><fmt:message key="home.go"/></a>
            </div>
            <div>
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <fmt:message key="lang.title"/>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" href="?sessionLocale=en"><fmt:message key="lang.en"/></a></li>
                            <li><a class="dropdown-item" href="?sessionLocale=ua"><fmt:message key="lang.ua"/></a></li>
                        </ul>
                    </li>
                    <c:if test="${!user.isPresent()}">
                        <li class="nav-item"><a class="nav-link"
                                                href="/exhibitions/redirect:/user/login.jsp"><fmt:message
                                key="common.login"/></a></li>
                        <li class="nav-item"><a class="nav-link"
                                                href="/exhibitions/redirect:/user/registration.jsp"><fmt:message
                                key="common.registration"/></a></li>
                    </c:if>
                    <c:if test="${user.isPresent()}">
                        <li class="nav-item"><a class="nav-link" href="/exhibitions/user/logout"><fmt:message
                                key="common.logout"/></a>
                        </li>
                        <c:if test="${user.get().role.ordinal() == 2}">
                            <li class="nav-item"><a class="nav-link"
                                                    href="${pageContext.request.contextPath}exhibitions/redirect:/admin/adminHome.jsp"><fmt:message
                                    key="common.adminHome"/></a></li>
                        </c:if>
                        <c:if test="${user.get().role.ordinal() > 0}">
                            <li class="nav-item"><a class="nav-link" href="/exhibitions/auth/userHome"><fmt:message
                                    key="common.userHome"/></a></li>
                        </c:if>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</header>

