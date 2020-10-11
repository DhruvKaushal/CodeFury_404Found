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
		UserID: ${employeeKey.employeeId}<br />
		UserName: ${employeeKey.employeeUsername}<br />
		Name: ${employeeKey.employeeName}<br />
		Contact No.: ${employeeKey.employeeContact}<br />
		Role: ${employeeKey.role}<br />
		Email: ${employeeKey.employeeEmail}<br />
		Password: ${employeeKey.employeePassword}<br />
		Signed Up on: ${employeeKey.signUpDate}<br />
		Last Sign In on: ${employeeKey.signInDate}<br />
	</p>
	<a href="employeedashboard.jsp">Back to Dashboard</a>
</body>
</html>