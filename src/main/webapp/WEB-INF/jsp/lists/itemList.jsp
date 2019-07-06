<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>List of items</title>
    <link href="/css/style.css" rel="stylesheet">
    <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../navbar.jsp" />
<div class="mainWindow">
    <div class="searchArea container">
        <h3>Search by type: </h3>
        <form action="${pageContext.request.contextPath}/item/type${type}">
            <select name="type" onchange="this.form.submit()">
                <option value=""></option>
                <c:forEach items="${itemTypes}" var="type">
                    <option>${type}</option>
                </c:forEach>
            </select>
        </form>
    </div>
    <div class="listArea container">
        <h3>Items: </h3>
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
            <c:forEach var="item" items="${items}">
                <tr>
                    <th>${item.itemId}</th>
                    <th>${item.itemName}</th>
                    <th>${item.itemType}</th>
                    <th>${item.quantity}</th>
                    <th>${item.price} $</th>
                    <th>
                        <a href="${pageContext.request.contextPath}/item/${item.itemId}">Details</a>
                    </th>
                </tr>
            </c:forEach>
        </table>
        <div class="btn">
            <a href="${pageContext.request.contextPath}/itemAdd">Add</a>
        </div>
    </div>
</div>
</body>
</html>
