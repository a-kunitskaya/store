<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US"/>
<html>
<head>
    <title>Registration</title>
    <jsp:include page="../resources/style.jsp"/>
</head>
<body>
<div>
    <fmt:bundle basename="${localePath}">
        <form action="/registration" method="POST">
            <input type="text" placeholder=
                <fmt:message key="username"/> class="form" name="username"/>
            <br/>
            <input type="text" placeholder=
                <fmt:message key="password"/> class="form" name="password"/>
            <br/>

            <label for="role"><fmt:message key="role"/></label>
            <select name="role">
                <option value="CUSTOMER"><fmt:message key="customer"/></option>
                <option value="VENDOR"><fmt:message key="vendor"/></option>
            </select>
            <br/>
            <input type="submit" class="button" value="<fmt:message key="registrationBtn"/>"/>
        </form>
    </fmt:bundle>
</div>
</body>
</html>


