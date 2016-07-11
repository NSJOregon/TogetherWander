<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css" />

<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Create a New Date</title>


  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
</head>
<body>

	<sf:form method="post"
		action="${pageContext.request.contextPath}/docreateevent"
		commandName="event">

		<table>
			<tr>
				<td id="Wander_Description">DATE</td>
				<td><sf:input id="datepicker" name="date" path="date" type="text" /><br />
				<sf:errors path="date" cssClass="error"></sf:errors></td>
			</tr>
			<tr>
				<td id="Wander_Description">NOTES</td>
				<td><sf:input name="notes" path="notes" type="text" /><br />
				<sf:errors path="notes" cssClass="error"></sf:errors></td>
			</tr>
			<tr>
				<td><sf:input name="id" path="id" type="hidden" value="${wander.id}" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input value="Add Date to Itinerary" type="submit" /></td>
			</tr>
		</table>

	</sf:form>

</body>
</html>