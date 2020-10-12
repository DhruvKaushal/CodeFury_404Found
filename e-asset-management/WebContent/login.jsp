<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="login.css">

</head>
<body>
	<div>
	<h1 style="background-color:grey;color:white;text-align:center">Login to AMP</h1>
	<br>
	
	<form action="LoginServlet" method="post">
		<div class="container">
			<h3 style="text-align:center">Login</h3>
			<hr>

			<label>
				<b>Username or Email</b> <input type="text" name="usercredential" required>
			</label>
			<br>
			<label>
				<b>Password</b> <input type="password" name="pass" id="psw" required>
			</label>
			<br>
			
			
			<label ><b>Role</b></label>
			<br>
			  <select name="role" id="role" style="width:80%;margin-left:10%;height:40px">
			    <option value="employee">Employee</option>
			    <option value="admin">Admin</option>
			  </select>
			
			<br>
			<button type="submit" class="login">Login</button>
			<br>
			<div id="message">
		</div>
		</div>
		
		<!-- <div class="container signin">
    		<p>Already have an account? <a href="#">Sign in</a>.</p>
  		</div> -->
	</form>
	</div>
	
</body>
</html>