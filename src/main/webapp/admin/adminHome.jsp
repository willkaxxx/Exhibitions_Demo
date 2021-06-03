<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 30.05.2021
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello, administrator</title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<a href="${pageContext.request.contextPath}/exhibitions/admin/manageHalls?page=1">Manage halls</a><br/>
<a href="${pageContext.request.contextPath}/exhibitions/admin/manageExhibitions?page=1">Manage exhibitions</a><br/>
</body>
</html>
