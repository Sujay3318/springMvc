<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html"%>

<html>

<head>

<title>User registration details</title>
</head>
<body bgcolor="#87ceff">
	<script>
		function validateform() {
			var firstName = document.myform.firstName.value;
			var lastName = document.myform.lastName.value;
			var mobileNumber = document.myform.mobileNumber.value;
			var email = document.myform.emailId.value;

			if (firstName == "") {
				alert("First Name is Mandatory");
				return false;
			}

			if (lastName == "") {
				alert("Last Name is Mandatory");
				return false;
			}

			if (mobileNumber == "") {
				alert("Mobile Number is Mandatory");
				return false;
			}

			if (email == "") {
				alert("Email is Mandatory");
				return false;
			}
		}
	</script>

	<h1 style="text-align: center;">User Registration Form</h1>

	<table align="center">
		<form:form commandName="user" name="myform"
			action="../../springmvc/user/add" method="post"
			onsubmit="return validateform()">

			<tr>
				<td><label>First Name:</label></td>
				<td><form:input path="firstName"></form:input></td>
			</tr>

			<tr>
				<td><label>Last Name:</label></td>
				<td><form:input path="lastName"></form:input></td>
			</tr>

			<tr>
				<td><form:label path="gender">Gender</form:label></td>
				<td><form:radiobutton path="gender" value="MALE" label="MALE" />
					<form:radiobutton path="gender" value="FEMALE" label="FEMALE" /></td>
			</tr>
			<tr>
				<td><label>Mobile Number:</label></td>
				<td><form:input path="mobileNumber"></form:input></td>
			</tr>

			<tr>
				<td><label>Email:</label></td>
				<td><form:input path="emailId"></form:input></td>
			</tr>

			<tr>
				<td><label class="label">password</label></td>
				<td><form:input type="password" path="password"></form:input></td>
			</tr>

			<tr>
				<td><input type="submit" style="float: right"></input></td>
			</tr>
			<tr>
				<td><input type="reset" style="float: right"></input></td>
			</tr>
		</form:form>
	</table>


</body>
</html>