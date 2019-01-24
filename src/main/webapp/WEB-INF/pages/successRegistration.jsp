<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US"/>
<html>
<head>
    <title>Successful Registration</title>
    <jsp:include page="../resources/style.jsp"/>
</head>
<body>
<div>
    <fmt:bundle basename="${localePath}">
        <h1><fmt:message key="welcome"/></h1>
        <p><fmt:message key="successRegistrationMsg"/></p>
        <form action="/index" method="GET">
            <input type="submit" class="button" value="<fmt:message key="logInBtn"/>"/>
        </form>
    </fmt:bundle>
</div>
</body>
</html>


