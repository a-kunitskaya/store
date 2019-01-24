<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Trade System</title>
    <jsp:include page="../resources/style.jsp"/>
</head>
<body>
<div>
    <fmt:bundle basename="${localePath}">
        <form action="/products" method="POST" name="user">
            <input type="text" placeholder=
                <fmt:message key="username"/> class="form" name="username"/>
            <br/>
            <input type="text" placeholder=
                <fmt:message key="password"/> class="form" name="password"/>
            <br/>
            <input type="submit" class="button" value="<fmt:message key="logInBtn"/>"/>
        </form>

        <c:if test="${not empty error}">
            <p>${error}</p>
        </c:if>

        <form action="/registration" method="GET">
            <input type="submit" class="button" value="<fmt:message key="registrationBtn"/>"/>
        </form>
    </fmt:bundle>
</div>
</body>
</html>

