<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add delivery</title>
    <link href="/css/style.css" rel="stylesheet">
    <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../navbar.jsp" />
    <div class="mainWindow infoArea container">
        <h3>Make an order: </h3>
        <a style="float: right; padding-right: 10px" href="${pageContext.request.contextPath}/cart">Back</a>
        <table class="table">
            <tbody>
            <tr>
                <td style="font-weight: bold">Ordered by:</td>
                <td>${customer}</td>
            </tr>
            <form:form action="${pageContext.request.contextPath}/deliverySave" modelAttribute="delivery" method="post">
            <tr>
                <td style="font-weight: bold">Delivery address:</td>
                <td><form:input path="deliveryAddress" /></td>
            </tr>
            <tr>
                <td style="font-weight: bold">Overall price:</td>
                <td>${cart.overallPrice} $</td>
            </tr>
            </tbody>
        </table>
        <h4>Ordered items:</h4>
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Type</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
            </thead>
            <c:forEach var="item" items="${cart.itemsInCart}">
                <tr>
                    <th>${item.itemDataId}</th>
                    <th>${item.itemName}</th>
                    <th>${item.itemType}</th>
                    <th>${item.quantity}</th>
                    <th>${item.price} $</th>
                </tr>
            </c:forEach>
            <tr></tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="btn-add" /></td>
            </tr>
            </form:form>
        </table>
    </div>
</body>
</html>
