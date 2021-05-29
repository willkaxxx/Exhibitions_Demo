<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 28.05.2021
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/exhibitions/registration" method="post">
        <p>Email<input type="email" name="email"></p><span><%= request.getAttribute("error")%></span>
        <p>Password<input type="text" name="pass"></p>
        <p><button type="submit">Register</button></p>
    </form>
</body>
</html>
