<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US"/>
<html>
<head>
    <title>Checkout</title>
    <jsp:include page="../resources/style.jsp"/>
</head>
<body>
<div>
    <fmt:bundle basename="${localePath}">
        <h1><fmt:message key="checkoutMsg"/></h1>
        <form action="/cancelOrder" method="GET">
            <input type="submit" class="button" value="<fmt:message key="cancelBtn"/>"/>
        </form>
    </fmt:bundle>
</div>
</body>
</html>


