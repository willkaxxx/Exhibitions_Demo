<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="helloServlet">Hello Servlet</a>
<hr/>
<form action = "helloServlet" method = "get" >
    <input type = "submit" value = "REQUEST" />
</form>
<br>
<a href="helloServlet2">Hello Servlet2</a>
<hr/>
<form action = "helloServlet2" method = "get" >
    <input type = "submit" value = "REQUEST" />
</form>
</body>
</html>