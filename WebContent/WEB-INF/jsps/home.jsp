<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	Welcome home
	<p>
		<c:forEach var="wander" items="${wanders}">
			<br>${wander.name}
			<br>${wander.notes}

        	<form id="showwander" action="${pageContext.request.contextPath}/showwander" method="POST">
				<input id="showwander" name="wander" type="hidden" value="${wander.id}" />
				<input type="submit" value="Show"/>
			</form>

			<form id="deletewander" action="${pageContext.request.contextPath}/removewander" method="POST">
				<input id="deletewander" name="wander" type="hidden" value="${wander.id}" />
				<input type="submit" value="delete"
					onClick="return confirm('Are you sure you want to leave this Wander?')" />
			</form>


		</c:forEach>
	<p>
		<a href="${pageContext.request.contextPath}/createwander">Create a
			New Wander</a>
	</p>
	
	<sec:authorize access="isAuthenticated()">
		<a class="login" href="<c:url value='/j_spring_security_logout'/>">Log
			out</a>
	</sec:authorize>

</body>
</html>