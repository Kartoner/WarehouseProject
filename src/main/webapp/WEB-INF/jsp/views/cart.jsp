<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>My cart</title>
    <link href="/css/style.css" rel="stylesheet">
    <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../navbar.jsp" />
<div class="mainWindow">
    <div class="listArea container">
        <h3>Items: </h3>
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Type</th>
                <th>Quantity</th>
                <th>Price per item</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach var="item" items="${items}">
                <tr>
                    <th>${item.itemDataId}</th>
                    <th>${item.itemName}</th>
                    <th>${item.itemType}</th>
                    <th>${item.quantity}</th>
                    <th>${item.price} $</th>
                    <th>
                        <a href="${pageContext.request.contextPath}/item/${item.itemDataId}">Details</a>
                    </th>
                    <th>
                        <a href="${pageContext.request.contextPath}/cart/${item.itemDataId}">Remove</a>
                    </th>
                </tr>
            </c:forEach>
            <tr>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th>Overall price: </th>
                <th>${overallPrice} $</th>
            </tr>
        </table>
        <div class="btn">
            <a href="${pageContext.request.contextPath}/deliveryAdd">Create order</a>
        </div>
    </div>
</div>
</body>
</html>
