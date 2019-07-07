<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>My profile</title>
    <link href="/css/style.css" rel="stylesheet">
    <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../navbar.jsp" />
    <div class="mainWindow infoArea container">
        <h3>My Profile: </h3>
        <table class="table">
            <tbody>
                <tr>
                    <td style="font-weight: bold">Username:</td>
                    <td>${user.username}</td>
                </tr>
                <tr>
                    <td style="font-weight: bold">Full name:</td>
                    <td>${user.fullname}</td>
                </tr>
                <tr>
                    <td style="font-weight: bold">Role:</td>
                    <td>${user.userRole.name}</td>
                </tr>
                <tr>
                    <td style="font-weight: bold">Address:</td>
                    <td>${user.address}</td>
                </tr>
                <tr>
                    <td style="font-weight: bold">Email:</td>
                    <td>${user.email}</td>
                </tr>
                <tr>
                    <td style="font-weight: bold">Phone number:</td>
                    <td>${user.phoneNumber}</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
