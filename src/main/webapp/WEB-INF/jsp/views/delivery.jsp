<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Delivery No. ${delivery.deliveryId}'s details</title>
        <link href="/css/style.css" rel="stylesheet">
        <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../navbar.jsp" />
        <div class="mainWindow infoArea container">
            <h3>Delivery No. ${delivery.deliveryId}'s details: </h3>
            <a style="float: right; padding-right: 10px" href="${pageContext.request.contextPath}/user">Back</a>
            <table class="table">
                <tbody>
                <tr>
                    <td style="font-weight: bold">Id:</td>
                    <td>${delivery.deliveryId}</td>
                </tr>
                <tr>
                    <td style="font-weight: bold">Ordered by:</td>
                    <td>${delivery.customerOrdering.fullName}</td>
                </tr>
                <tr>
                    <td style="font-weight: bold">Accepted By:</td>
                    <td>${delivery.employeeAccepting.fullName}</td>
                </tr>
                <tr>
                    <td style="font-weight: bold">Delivery address:</td>
                    <td>${delivery.deliveryAddress}</td>
                </tr>
                <tr>
                    <td style="font-weight: bold">Status:</td>
                    <td>${delivery.deliveryStatus}</td>
                </tr>
                <tr>
                    <td style="font-weight: bold">Overall price:</td>
                    <td>${delivery.overallPrice} $</td>
                </tr>
                <tr>
                    <td style="font-weight: bold">Paid:</td>
                    <td>${delivery.paid}</td>
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
                    <th></th>
                </tr>
                </thead>
                <c:forEach var="item" items="${delivery.itemsOrdered}">
                    <tr>
                        <th>${item.itemDataId}</th>
                        <th>${item.itemName}</th>
                        <th>${item.itemType}</th>
                        <th>${item.quantity}</th>
                        <th>${item.price} $</th>
                        <th>
                            <a href="${pageContext.request.contextPath}/item/${item.itemDataId}">Details</a>
                        </th>
                    </tr>
                </c:forEach>
            </table>
            <div class="btn btn-add">
                <a href="${pageContext.request.contextPath}/delivery/${delivery.deliveryId}/update">Update</a>
            </div>
            <div class="btn btn-remove">
                <a href="${pageContext.request.contextPath}/delivery/${delivery.deliveryId}/remove">Remove</a>
            </div>
        </div>
    </body>
</html>
