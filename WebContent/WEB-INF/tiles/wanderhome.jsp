<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div id="Wander_Title">${wander.name}</div>
	<hr>
	<div id="Wander_Description">${wander.notes}</div>
	<hr>
	<br>
	<div id="Wander_Description">Travelers on this Trip:</div>

	<c:forEach var="traveler" items="${travelers}">
		<br>
		<div id="Wander_Data">${traveler.username}<div>


				<c:if
					test="${(admintraveler.role == 'admin')&&(traveler.username != admintraveler.username)}">
					<form id="deletetraveler"
						action="${pageContext.request.contextPath}/removetraveler"
						method="POST">
						<input name="wander" type="hidden" value="${wander.id}" /> <input
							name="traveler" type="hidden" value="${traveler.username}" /> <input
							type="submit" value="delete"
							onClick="return confirm('Are you sure you want to delete this Traveler?')" />
					</form>
				</c:if>
	</c:forEach>
	<br>
	<br>
	<sf:form method="post"
		action="${pageContext.request.contextPath}/showwander"
		commandName="traveler">

		<table>
			<tr>
				<td id="Wander_Description">Enter Traveler's Username:</td>
			</tr>
			<tr>
				<td>${error}</td>
			</tr>
			<tr>
				<td><sf:input class="control" name="username" path="username"
						type="text" /><br />
						<sf:errors path="username" cssClass="error"></sf:errors>
				</td>
			</tr>
			<tr>
				<td><input id="addTraveler" name="wander" type="hidden"
					value="${wander.id}" /></td>
			</tr>
			<tr>
				<td><input value="Add Traveler to Trip" type="submit" /></td>
			</tr>
		</table>

	</sf:form>

	<hr>
	<div id="Wander_Description">Trip Itinerary:</div>
	<p>
		<c:forEach var="event" items="${events}">

			<br>
			<fmt:formatDate type="date" value="${event.date}" />
			<br>${event.notes}
		
		<table>
				<tr>
					<td>
						<form id="deleteevent"
							action="${pageContext.request.contextPath}/removeevent"
							method="POST">
							<input name="wander" type="hidden" value="${wander.id}" /> <input
								name="event" type="hidden" value="${event.datesid}" /> <input
								type="submit" value="Delete Event"
								onClick="return confirm('Are you sure you want to delete this Event?')" />
						</form>
					</td>
					<td>

						<form id="editevent"
							action="${pageContext.request.contextPath}/editevent"
							method="POST">
							<input name="wander" type="hidden" value="${wander.id}" /> <input
								name="event" type="hidden" value="${event.datesid}" /> <input
								type="submit" value="Edit Event"
								onClick="return confirm('Are you sure you want to edit this Event?')" />
						</form>
					</td>
				</tr>
			</table>

		</c:forEach>

	</p>


	<form id="createvent"
		action="${pageContext.request.contextPath}/createevent" method="POST">
		<input id="createevent" name="wander" type="hidden"
			value="${wander.id}" /> <input type="submit"
			value="Add Event to Itinerary" />
	</form>


</body>
</html>
