<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />


<script type="text/javascript">
	$(document).ready(function() {
		document.f.j_username.focus();
	});
</script>

<style>

body{
    background-size: cover;
	background-image: url("${pageContext.request.contextPath}/static/images/tent.jpg");
}
</style>

<div id="Wander_Description"><h3>Login with Username and Password</h3></div>

<c:if test="${param.error != null}">

	<p class="error">Login failed. Check that your username and
		password are correct.</p>

</c:if>


<form name='f'
	action='${pageContext.request.contextPath}/j_spring_security_check'
	method='POST'>
	<table class="formtable">
		
		<tr>
			<td>Username</td>
			<td><input type='text' name='j_username' value=''></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type='password' name='j_password' /></td>
		</tr>
		<tr>
			<td>Remember me:</td>
			<td><input type='checkbox' name='_spring_security_remember_me'
				checked="checked" /></td>
		</tr>
		<tr>
			<td colspan='2'><input name="submit" type="submit" value="Login" /></td>
		</tr>
		
	</table>
</form>

<p>
	<a class="label" style="color:white;" href="<c:url value="/newaccount"/>">Create new account</a>
</p>


