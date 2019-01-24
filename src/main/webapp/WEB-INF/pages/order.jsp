<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US"/>
<html>
<head>
    <title>Order</title>
    <jsp:include page="../resources/style.jsp"/>
</head>
<body>
<fmt:bundle basename="${localePath}">
 <h1><fmt:message key="viewOrderMsg"/></h1>
    <br/>
<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>PRICE</th>
        <th>COUNT</th>
    </tr>
    <c:forEach var="product" items="${order}">
    <tr>
        <td>${product.key.id}</td>
        <td>${product.key.name}</td>
        <td>${product.key.price}</td>
        <td>${product.value}</td>
        </c:forEach>
</table>


    <form action="/checkout" method="GET">
        <br/>
        <input type="submit" class="button" value="<fmt:message key="checkoutBtn"/>"/>
    </form>
</fmt:bundle>
</body>
</html>
