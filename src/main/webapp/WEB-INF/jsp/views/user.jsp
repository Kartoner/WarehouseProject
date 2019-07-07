<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>${user.username}'s profile:</title>
        <link href="/css/style.css" rel="stylesheet">
        <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../navbar.jsp" />
        <div class="mainWindow infoArea container">
            <h3>${user.username}'s profile:</h3>
            <a style="float: right; padding-right: 10px" href="${pageContext.request.contextPath}/user">Back</a>
            <table class="table">
                <tbody>
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
            <div class="btn btn-add">
                <a href="${pageContext.request.contextPath}/user/${user.userId}/update">Update</a>
            </div>
            <div class="btn btn-remove">
                <a href="${pageContext.request.contextPath}/user/${user.userId}/remove">Remove</a>
            </div>
        </div>
    </body>
</html>
