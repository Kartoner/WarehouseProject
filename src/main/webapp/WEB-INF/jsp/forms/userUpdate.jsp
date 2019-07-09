<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit user</title>
    <link href="/css/style.css" rel="stylesheet">
    <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../navbar.jsp" />
    <div class="mainWindow infoArea container">
        <form:form action="${pageContext.request.contextPath}/user/${id}/save" modelAttribute="user" method="post">
            <h3>Edit user: </h3>
            <table class="table">
                <tbody>
                <tr>
                    <td><label>Username:</label></td>
                    <td><form:input path="username" /></td>
                </tr>
                <tr>
                    <td><label>Password:</label></td>
                    <td><form:input path="password" /></td>
                </tr>
                <tr>
                    <td><label>First name:</label></td>
                    <td><form:input path="firstName" /></td>
                </tr>
                <tr>
                    <td><label>Last name:</label></td>
                    <td><form:input path="lastName"/></td>
                </tr>
                <tr>
                    <td><label>Address:</label></td>
                    <td><form:input path="address" /></td>
                </tr>
                <tr>
                    <td><label>Email:</label></td>
                    <td><form:input path="email" /></td>
                </tr>
                <tr>
                    <td><label>Phone number:</label></td>
                    <td><form:input path="phoneNumber" /></td>
                </tr>
                <tr>
                    <td><label></label></td>
                    <td><input type="submit" value="Save" class="btn-add" /></td>
                </tr>
                </tbody>
            </table>
        </form:form>
    </div>
</body>
</html>
