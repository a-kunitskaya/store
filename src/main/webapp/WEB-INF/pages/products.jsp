<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US"/>
<html>
<head>
    <title>Products</title>
</head>
<body>
<fmt:bundle basename="com.kunitskaya.service.configuration.i18n.EnLocaleBundle">
    <c:if test="${not empty cancelledOrder}">
        <h1><fmt:message key="cancelledOrderMsg"/></h1>
    </c:if>
    <c:if test="${not empty error}">
        <p>${error}</p>
    </c:if>

    <table>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>PRICE</th>
            <th>ACTION</th>
        </tr>
        <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                <form action="/addProduct" method="POST">
                    <input type="hidden" name="productId" value="${product.id}"/>
                    <input type="submit" class="button" value="<fmt:message key="addBtn"/>"/>
                </form>
                <br/>
                <form action="/deleteProduct" method="POST">
                    <input type="hidden" name="productId" value="${product.id}"/>
                    <input type="submit" class="button" value="<fmt:message key="removeBtn"/>"/>
                </form>
            </td>
            </c:forEach>
    </table>
    <form action="/viewOrder" method="GET">
        <input type="submit" class="button" value="<fmt:message key="viewOrderBtn"/>"/>
    </form>

</fmt:bundle>
</body>
</html>
