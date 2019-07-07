<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>List of users</title>
    <link href="/css/style.css" rel="stylesheet">
    <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../navbar.jsp" />
    <div class="mainWindow">
        <div class="searchArea container">
            <h3>Search by role: </h3>
            <form action="${pageContext.request.contextPath}/user/role${role}">
                <select name="role" onchange="this.form.submit()">
                    <option value=""></option>
                    <c:forEach items="${roles}" var="role">
                        <option>${role}</option>
                    </c:forEach>
                </select>
            </form>
        </div>
        <div class="listArea container">
            <h3>Users: </h3>
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Full name</th>
                        <th>Role</th>
                        <th>Address</th>
                        <th>Email</th>
                        <th>Phone number</th>
                        <th></th>
                    </tr>
                </thead>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <th>${user.userId}</th>
                        <th>${user.username}</th>
                        <th>${user.fullname}</th>
                        <th>${user.userRole.name}</th>
                        <th>${user.address}</th>
                        <th>${user.email}</th>
                        <th>${user.phoneNumber}</th>
                        <th>
                            <a href="${pageContext.request.contextPath}/user/${user.userId}">Details</a>
                        </th>
                    </tr>
                </c:forEach>
            </table>
            <div class="btn btn-add">
                <a href="${pageContext.request.contextPath}/userAdd">Add</a>
            </div>
        </div>
    </div>
</body>
</html>
