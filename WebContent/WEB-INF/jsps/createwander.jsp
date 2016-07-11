<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css" />

<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>

	<sf:form method="post"
		action="${pageContext.request.contextPath}/docreate"
		commandName="wander">

		<table>
			<tr>
				<td>Name:</td>
				<td><sf:input name="name" path="name" type="text" /><br />
				<sf:errors path="name" cssClass="error"></sf:errors></td>
			</tr>
			<tr>
				<td>Notes:</td>
				<td><sf:input name="notes" path="notes" type="text" /><br />
				<sf:errors path="notes" cssClass="error"></sf:errors></td>
			</tr>
			<tr>
				<td></td>
				<td><input value="Create Wander" type="submit" /></td>
			</tr>
		</table>

	</sf:form>

</body>
</html>