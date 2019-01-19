<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US"/>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div>
    <fmt:bundle basename="com.kunitskaya.service.configuration.i18n.EnLocaleBundle">
        <form action="/products" method="POST" name="user">
            <input type="text" placeholder=<fmt:message key="username"/> class="form" name="username"/>
            <br/>
            <input type="text" placeholder=<fmt:message key="password"/> class="form" name="password"/>
            <br/>
            <select>
                <option value="merchant"><fmt:message key="vendor"/></option>
                <option value="customer"><fmt:message key="customer"/></option>
            </select>
            <input type="submit" class="button" value="<fmt:message key="submit"/>"/>
        </form>
    </fmt:bundle>
</div>
</body>
</html>


