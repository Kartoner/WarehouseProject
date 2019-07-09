<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add item</title>
    <link href="/css/style.css" rel="stylesheet">
    <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../navbar.jsp" />
    <div class="mainWindow infoArea container">
        <form:form action="${pageContext.request.contextPath}/itemSave" modelAttribute="item" method="post">
            <h3>Add new item: </h3>
            <table class="table">
                <tbody>
                    <tr>
                        <td><label>Name:</label></td>
                        <td><form:input path="itemName" /></td>
                    </tr>
                    <tr>
                        <td><label>Type:</label></td>
                        <td><form:select path="itemType" items="${itemTypes}" /></td>
                    </tr>
                    <tr>
                        <td><label>Description:</label></td>
                        <td><form:input path="itemDescription" /></td>
                    </tr>
                    <tr>
                        <td><label>Quantity:</label></td>
                        <td><form:input path="quantity"/></td>
                    </tr>
                    <tr>
                        <td><label>Price:</label></td>
                        <td><form:input path="price" /></td>
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
