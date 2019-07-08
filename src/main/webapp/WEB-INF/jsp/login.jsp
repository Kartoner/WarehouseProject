<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
    <link href="/css/style.css" rel="stylesheet">
    <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="navbar.jsp" />
    <div class="mainWindow">
        <div class="infoArea">
            <form method="POST" action="${pageContext.request.contextPath}/login" class="form-signin">
                <h2 class="form-heading">Log in</h2>

                <div class="form-group">
                    <input name="username" type="text" class="form-control" placeholder="Username"
                        autofocus="true"/>
                    <input name="password" type="password" class="form-control" placeholder="Password"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
