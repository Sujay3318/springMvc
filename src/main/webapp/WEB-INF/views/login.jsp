<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html>

</head>


<meta charset="ISO-8859-1">

	
	
<body bgcolor="#87ceff">
	<script>
		function validateform() {
			var email = document.myform.emailId.value;
			var password = document.myform.password.value;

			if (email == "") {
				alert("Email is Mandatory");
				return false;
			}

			if (password == "") {
				alert("Password is Mandatory");
				return false;
			}

		}
	</script>


<div align='center'>
	<h2>Login </h2>
	<form:form   commandName="user"
		action="../../springmvc/user/login/check" method="post"
		onsubmit="return validateform()">
		<table>
			<tr>
				<td><label class="label">Email:</label></td>
				<td><form:input path="emailId"></form:input></td>
			</tr>
			<tr>
				<td><label class="label">password</label></td>
				<td><form:input type="password" path="password"></form:input></td>
			</tr>
				<tr>
					<td><input type="submit" value="submit" /></td>
				</tr>

				<tr>
				<td><input type="reset" style="float: right"></input></td>
			</tr>
		</table>
	</form:form>
	</div>
	
	<form align='center'
		action='http://localhost:8080/springmvc/user/register'>
		
		<div align='center'>
			<input type='submit' value='New registration' />
		</div>
	</form>
	
	
	
	<form align='center'
		action='http://localhost:8080/springmvc/user/password/reset'>
		<div align='center'>
			<input type='submit' value='Forgot password' />
			
		</div>
	</form>

</body>
</html>