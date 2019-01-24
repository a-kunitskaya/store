<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setLocale value="en_US"/>
<html>
<head>
    <title>Main</title>
    <jsp:include page="../resources/style.jsp"/>
</head>
<body>
<div>
    <h1>Please select language</h1>
    <form action="/index" method="GET">
    <select name="locale">
        <option value="en_US">EN</option>
        <option value="ru_RU">RU</option>
    </select>
    <input type="submit" class="button" value="SUBMIT"/>
    </form>
    <br/>
</div>
</body>
</html>
