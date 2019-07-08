<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Welcome</title>
    <link href="/css/style.css" rel="stylesheet">
    <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="navbar.jsp" />
    <div class="mainWindow">
        <div class="infoArea">
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name == null}">
                    <h1>Welcome to the Warehouse Service!</h1>
                    <h4>Please <a href="${pageContext.request.contextPath}/login">log in</a></h4>
                </c:when>
                <c:otherwise>
                    <h1>Hello ${pageContext.request.userPrincipal.name}!</h1>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>
