<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Admin Login Page</h2>
<form action = "LoginServlet" method = "post">
	<label>
		UserName/Email <input type = "text" name = "user">
	</label>
    <br />
	<label>
		Password <input type = "password" name = "pass">
	</label>
	<br />
	<input type = "submit" value = "Submit">
</form>	
</body>
</html>