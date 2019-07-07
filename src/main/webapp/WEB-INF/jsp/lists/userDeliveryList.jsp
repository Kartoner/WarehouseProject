<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>My deliveries</title>
    <link href="/css/style.css" rel="stylesheet">
    <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../navbar.jsp" />
<div class="mainWindow">
    <div class="listArea container">
        <h3>My deliveries: </h3>
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Ordered by</th>
                <th>Accepted by</th>
                <th>Delivery address</th>
                <th>Status</th>
                <th>Overall price</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach var="delivery" items="${deliveries}">
                <tr>
                    <th>${delivery.deliveryId}</th>
                    <th>${delivery.customerOrdering.fullName}</th>
                    <th>${delivery.employeeAccepting.fullName}</th>
                    <th>${delivery.deliveryAddress}</th>
                    <th>${delivery.deliveryStatus}</th>
                    <th>${delivery.overallPrice} $</th>
                    <th>
                        <a href="${pageContext.request.contextPath}/delivery/${delivery.deliveryId}">Details</a>
                    </th>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>

