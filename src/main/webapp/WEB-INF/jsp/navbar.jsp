<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="navbar-container container">
    <h2>Menu: </h2>
    <ul class="nav">
        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <security:authorize access="hasAuthority('ADMIN')">
                <li><a href="${pageContext.request.contextPath}/h2-console">Database</a></li>
                <li><a href="${pageContext.request.contextPath}/user">Users</a></li>
            </security:authorize>
            <security:authorize access="hasAnyAuthority('ADMIN', 'EMPLOYEE')">
                <li><a href="${pageContext.request.contextPath}/delivery" role="button">Deliveries</a></li>
            </security:authorize>
            <li><a href="${pageContext.request.contextPath}/item" role="button">Items</a></li>
            <li><a href="${pageContext.request.contextPath}/user/details" role="button">Profile</a></li>
            <li><a href="${pageContext.request.contextPath}/cart" role="button">My cart</a></li>
            <li><a href="${pageContext.request.contextPath}/user/delivery" role="button">My deliveries</a></li>
            <li>
                <form id="logoutForm" method="POST" action="${pageContext.request.contextPath}/logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                <a onclick="document.forms['logoutForm'].submit()" role="button">Logout</a>
            </li>
        </c:if>
    </ul>
</div>

