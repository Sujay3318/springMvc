<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html"%>

<html>

<head>

<title>password reset</title>
</head>
<body bgcolor="#87ceff">

	<h3>Password and otp validation before updating password</h3>
	<table align="left">
		<form:form commandName="user" action="../../user/password/check"
			method="post">

			<tr>
				<td><label>Enter Registered Email:</label></td>
				<td><form:input path="emailId"></form:input></td>
			</tr>
			<tr>
				<td><label>Otp:</label></td>
				<td><form:input path="otp"></form:input></td>
			</tr>

			<tr>
				<td><label>password:</label></td>
				<td><form:input path="password" type="password"></form:input></td>
			</tr>

			<tr>
				<td><input type="submit" style="float: right"></input></td>
			</tr>
		</form:form>
	</table>



</body>
</html>