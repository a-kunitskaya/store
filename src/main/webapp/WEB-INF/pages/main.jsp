
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US"/>
<html>
<head>
    <title>Main</title>
</head>
<body>
<div>
    <h1>Please select language:</h1>
    <form action="/selectLocale" method="GET" name="locale">
        <select name="locale">
            <option value="en_US">en</option>
            <option value="ru_RU">ru</option>
        </select>
        <input type="submit" class="button" value="SUBMIT"/>
    </form>
    <br/>

    <c:choose>
        <c:when test="${locale == null}">
            <c:set var="localeLocation" scope="session"
                   value="com.kunitskaya.service.configuration.i18n.EnLocaleBundle"/>
        </c:when>
        <c:when test="${locale == en_US}">
            <c:set var="localeLocation" scope="session"
                   value="com.kunitskaya.service.configuration.i18n.EnLocaleBundle"/>
        </c:when>
        <c:otherwise>
            <c:set var="localeLocation" scope="session"
                   value="com.kunitskaya.service.configuration.i18n.RuLocaleBundle"/>
        </c:otherwise>
    </c:choose>

    <form action="/index" method="GET">
        <input type="submit" class="button" value="INDEX PAGE"/>
    </form>
</div>
</body>
</html>
