<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Your Profile Details:</h3>
	<p>
		UserID: ${userKey.userId}<br />
		UserName: ${userKey.userName}<br />
		Name: ${userKey.name}<br />
		Role: ${userKey.role}<br />
		Email: ${userKey.email}<br />
		Password: ${userKey.password}<br />
		Contact No.: ${userKey.contact}<br />
		Signed Up on: ${userKey.signUpDate}<br />
		Last Sign In on: ${userKey.signInDate}<br />
	</p>
	<a href="loginsuccess.jsp">Back to Dashboard</a>
</body>
</html>