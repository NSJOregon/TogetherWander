<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/scripts/jquery.js"></script>
<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css" />

<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Create New Account</title>


<script type="text/javascript">
	function onLoad() {

		$("#password").keyup(checkPasswordsMatch);
		$("#confirmpass").keyup(checkPasswordsMatch);

		$("#details").submit(canSubmit);
	}

	function canSubmit() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();

		if (password != confirmpass) {
			alert("<fmt:message key='UnmatchedPasswords.user.password' />")
			return false;
		} else {
			return true;
		}
	}

	function checkPasswordsMatch() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();

		if (password.length > 3 || confirmpass.length > 3) {

			if (password == confirmpass) {
				$("#matchpass").text(
						"<fmt:message key='MatchedPasswords.user.password' />");
				$("#matchpass").addClass("valid");
				$("#matchpass").removeClass("error");
			} else {
				$("#matchpass")
						.text(
								"<fmt:message key='UnmatchedPasswords.user.password' />");
				$("#matchpass").addClass("error");
				$("#matchpass").removeClass("valid");
			}
		}
	}

	$(document).ready(onLoad);
</script>




</head>
<body>

	<div id="Wander_Description"><h2>Create New Account</h2></div>

	<sf:form method="post"
		action="${pageContext.request.contextPath}/createaccount"
		commandName="user">

		<table class="formtable">
			<tr>
				<td class="label">Name:</td>
				<td><sf:input class="control" path="name" name="name"
						type="text" /><br /> <sf:errors path="name" cssClass="error"></sf:errors></td>
			</tr>
			<tr>
				<td class="label">Username:</td>
				<td><sf:input class="control" path="username" name="username"
						type="text" /><br /> <sf:errors path="username" cssClass="error"></sf:errors></td>
			</tr>
			<tr>
				<td class="label">Email:</td>
				<td><sf:input class="control" path="email" name="email"
						type="text" /><br /> <sf:errors path="email" cssClass="error"></sf:errors></td>
			</tr>
			<tr>
				<td class="label">Password:</td>
				<td><sf:input id="password" class="control" path="password"
						name="password" type="password" />
					<div class="error">
						<sf:errors path="password"></sf:errors>
					</div></td>
			</tr>

			<tr>
				<td class="label">Confirm Password:</td>
				<td><input id="confirmpass" class="control" name="confirmpass"
					type="password" />
					<div id="matchpass"></div></td>
			</tr>

			<tr>
				<td class="label"></td>
				<td><input class="control" value="Create account" type="submit" /></td>
			</tr>
		</table>

	</sf:form>

</body>
</html>