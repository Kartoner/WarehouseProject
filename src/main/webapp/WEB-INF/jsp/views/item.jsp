<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${item.itemName}</title>
    <link href="/css/style.css" rel="stylesheet">
    <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../navbar.jsp" />
    <div class="mainWindow infoArea container">
        <h3>${item.itemName}'s details:</h3>
        <a style="float: right; padding-right: 10px" href="${pageContext.request.contextPath}/item">Back</a>
        <table class="table">
            <tbody>
            <tr>
                <td style="font-weight: bold">Item name:</td>
                <td>${item.itemName}</td>
            </tr>
            <tr>
                <td style="font-weight: bold">Type:</td>
                <td>${item.itemType}</td>
            </tr>
            <tr>
                <td style="font-weight: bold">Description:</td>
                <td>${item.itemDescription}</td>
            </tr>
            <tr>
                <td style="font-weight: bold">Price:</td>
                <td>${item.price} $</td>
            </tr>
            <tr>
                <td style="font-weight: bold">In stock:</td>
                <td>${item.quantity}</td>
            </tr>
            </tbody>
        </table>
        <form action="${pageContext.request.contextPath}/cartAdd/${item.itemId}${quantity}">
            <div>
                <table class="table">
                    <tr>
                        <td>Amount: </td>
                        <td><input type="number" id="quantity" name="quantity"></td>
                        <td><input class="btn btn-add" type="submit" value="Add to cart"></td>
                    </tr>
                </table>
            </div>
        </form>
        <security:authorize access="hasAnyAuthority('ADMIN', 'EMPLOYEE')">
            <div class="btn btn-add">
                <a href="${pageContext.request.contextPath}/item/${item.itemId}/update">Update</a>
            </div>
            <div class="btn btn-remove">
                <a href="${pageContext.request.contextPath}/item/${item.itemId}/remove">Remove</a>
            </div>
        </security:authorize>
    </div>
</body>
</html>
