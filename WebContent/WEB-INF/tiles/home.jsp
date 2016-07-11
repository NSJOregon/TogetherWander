<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



	<div id=Wander_Description>YOUR UPCOMING TRIPS</div>
<div>
	<p>
		<c:forEach var="wander" items="${wanders}">
        <table style="border-spacing: 50px 20px; margin-left:auto; margin-right:auto;">
			<tr>
				<td>
					<div id="Wander_Title">${wander.name}</div>
				</td>
				<td></td>
				<td>
					<form id="showwander"
						action="${pageContext.request.contextPath}/showwander"
						method="POST">
						<input id="showwander" name="wander" type="hidden"
							value="${wander.id}" /> <input class="button button1"
							type="submit" value="Show" />
					</form>
				</td>
				<td>
					<form id="deletewander"
						action="${pageContext.request.contextPath}/removewander"
						method="POST">
						<input id="deletewander" name="wander" type="hidden"
							value="${wander.id}" /> <input type="submit" value="Delete"
							onClick="return confirm('Are you sure you want to leave this Wander?')" />
					</form>
				</td>
			</tr>
			</table>
		</c:forEach>
	<p>
		<a class="newwander" href="${pageContext.request.contextPath}/createwander">Create a
			new Wander</a>
	</p>
</div>

</body>
</html>