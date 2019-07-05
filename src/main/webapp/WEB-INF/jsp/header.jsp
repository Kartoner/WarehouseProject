<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <ul class="nav">
        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/user">Users</a></li>
        <li><a href="${pageContext.request.contextPath}/delivery" role="button">Deliveries</a></li>
        <li><a href="${pageContext.request.contextPath}/item" role="button">Items</a></li>
        <li><a href="${pageContext.request.contextPath}/user/details" role="button">Profile</a></li>
        <li><a href="${pageContext.request.contextPath}/user/delivery" role="button">My deliveries</a></li>
        <li><a href="${pageContext.request.contextPath}/user/logout" role="button">Logout</a></li>
    </ul>
</div>

