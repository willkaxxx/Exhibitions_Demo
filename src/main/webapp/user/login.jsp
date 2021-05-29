<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 30.05.2021
  Time: 2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/exhibitions/login" method="get">
    <p>Email <input type="email" name="email"> ${email_error}</p>
    <p>Password<input type="password" name="pass"> ${pass_error}</p>
    <p>${exist_error}</p>
    <p><button type="submit">Login</button></p>
</form>
</body>
</html>
