<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>




 
<a class="title" href="<c:url value='/'/>">TOGETHER WANDER</a>


<sec:authorize access="isAuthenticated()">
<div id="Wander_Description"><a class="login" href="<c:url value='/j_spring_security_logout'/>">Log out</a></div>
</sec:authorize>

