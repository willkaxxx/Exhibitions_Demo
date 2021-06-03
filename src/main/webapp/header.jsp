<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 03.06.2021
  Time: 2:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <c:if test="${user != null}">
        <a style="alignment: right" href="${pageContext.request.contextPath}exhibitions/user/logout">logout</a>
        <c:if test="${user.get().role.ordinal() == 2}">
            <a href="${pageContext.request.contextPath}exhibitions/redirect:/admin/adminHome.jsp">Admin home</a><br/>
        </c:if>
    </c:if>
    <c:if test="${user == null}">
        <a href="${pageContext.request.contextPath}exhibitions/redirect:/user/login.jsp">login</a>
        <a style="alignment: right"
           href="${pageContext.request.contextPath}exhibitions/redirect:/user/registration.jsp">registration</a>
    </c:if>
    <hr>
</header>

