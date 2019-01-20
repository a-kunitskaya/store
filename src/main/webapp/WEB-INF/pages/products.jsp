<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US"/>
<html>
<head>
    <title>Products</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>PRICE</th>
    </tr>
    <c:forEach var="product" items="${products}">
    <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.price}</td>
        </c:forEach>
</table>

<fmt:bundle basename="com.kunitskaya.service.configuration.i18n.EnLocaleBundle">
    <form action="/addProduct" method="POST">
        <br/>
        <input type="text" placeholder="<fmt:message key="addProductMsg"/>" name="productId"/>
        <br/>
        <input type="submit" class="button" value="<fmt:message key="addBtn"/>"/>
    </form>
</fmt:bundle>
</body>
</html>
