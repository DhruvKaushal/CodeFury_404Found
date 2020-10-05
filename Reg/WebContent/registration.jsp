<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="registration.css">
<script src="registraion.js"></script>
</head>
<body>
	<div>
	<h1 style="background-color:grey;color:white;text-align:center">WELCOME TO THE REGISTRATION PAGE</h1>
	<br>
	
	<form action="RegistrationServlet" method="post">
		<div class="container">
			<h3 style="text-align:center">Please enter the following details to complete your registration</h3>
			<hr>
			<label>
				<b>Name</b> <input type="text" name="name">
			</label>
			<br>
			<label>
				<b>Role</b>
				<select id="roles" name="role" style="width:80%;height:40px;" required>
 					<option value="Admin">Admin</option>
  					<option value="Employee">Employee</option>
				</select>
			</label>
			<br>
			<label>
				<b>Contact Number</b> <input type="text" name="contact">
			</label>
			<br>
			<label>
				<b>E-mail</b> <input type="email" name="mail" required>
			</label>
			<br>
			<label>
				<b>Username</b> <input type="text" name="un" required>
			</label>
			<br>
			<label>
				<b>Password</b> <input type="password" name="ps" id="psw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required>
			</label>
			<br>
			<button type="submit" class="register">Register</button>
			<br>
			<div id="message">
		</div>
		</div>
		
		<div class="container signin">
    		<p>Already have an account? <a href="#">Sign in</a>.</p>
  		</div>
	</form>
	</div>
	
</body>
</html>