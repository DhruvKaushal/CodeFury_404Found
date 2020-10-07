<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include  file="headerfile.jsp" %>
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
	<h1 style="background-color:grey;color:white;text-align:center;font-size:44px">WELCOME TO THE REGISTRATION PAGE</h1>
	<br>
	
	<form action="RegistrationServlet" method="post">
		<div class="container">
			<h3 style="text-align:center; font-size:25px">Please enter the following details to complete your registration</h3>
			<hr>
			<h3 style = "color:red;margin:auto;">${err}</h3>
			<label>
				<b>Name</b> 
			</label><br>
			<input type="text" name="name">
			<br>
			<label>
				<b>Role</b>
			</label><br>
			<input type="text" value="Employee" disabled>
			<br>
			<label>
				<b>Contact Number</b> 
			</label><br>
			<input type="text" name="contact">
			<br>
			<label>
				<b>E-mail</b> 
			</label><br>
			<input type="email" name="mail" required>
			<br>
			<label>
				<b>Username</b> 
			</label><br>
			<input type="text" name="un" required>
			<br>
			<label>
				<b>Password</b> 
			</label><br>
			<input type="password" name="ps" id="psw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required>
			<br>
			<label>
				<b>Confirm Password</b> 
			</label><br>
			<input type="password" name="pscnf" id="pswc" required>
			<br>
			<button type="submit" class="register" style="">Register</button>
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