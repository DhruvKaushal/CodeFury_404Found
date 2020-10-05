<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p style="color: green;">You have successfully registered. Your User ID is: ${newUser.userId}</p>
	<jsp:include page="login.html"></jsp:include>
</body>
</html>