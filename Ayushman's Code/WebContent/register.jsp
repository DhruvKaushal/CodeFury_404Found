<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Employee Registeration Page</h2>
<form action = "RegisterServlet" method = "post">
	<label>
		Name <input type = "text" name = "name">
	</label>
	<br />
	<label>
		Username <input type = "text" name = "uname">
	</label>
	<br/>
	<label>
		Password <input type = "password" name = "pass">
	</label>
	<br />
	<label>
		Confirm Password <input type = "password" name = "confpass">
	</label>
	<br/>
	<label>
		Contact <input type = "text" name = "contact">
	</label>
	<br />
	<label>
		Email <input type = "text" name = "email">
	</label>
	<br />
		<input type = "submit" value = "Register">
</form>
</body>
</html>