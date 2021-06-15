<%--
  Created by IntelliJ IDEA.
  User: willk
  Date: 06.06.2021
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <fmt:setLocale value="${lang}"/>
    <fmt:setBundle basename="local"/>
    <title><fmt:message key="404.title" /></title>
</head>
<body>
        <div class="row justify-content-center">
            <div class="col-md-12 col-sm-12">
                <div class="card shadow-lg border-0 rounded-lg mt-5 mx-auto" style="width: 30rem;">
                    <h3 class="card-header display-1 text-muted text-center">
                        404
                    </h3>

                    <span class="card-subtitle mb-2 text-muted text-center">
                Page Could Not Be Found
            </span>
                    <div class="card-body mx-auto">
                        <a type="button" href="/exhibitions/index?page=1"
                           class="btn btn-sm btn-info text-white"><fmt:message key="error.goHome" /></a>
                    </div>
                </div>
            </div>
        </div>
</body>
</html>
