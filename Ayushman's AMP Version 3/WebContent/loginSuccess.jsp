<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2>Hello.You have successfully logged in.</h2>
<p>Id: ${EmployeeDetails.user_id}, Name : ${EmployeeDetails.name}, Phone: ${EmployeeDetails.contact}</p>
<a href="BorrowServlet"><h2>Please click on the link to borrow item.</h2></a><br/>
<a href="ReturnServlet"><h2>Please click on the link to return item.</h2></a><br/>
<a href="LogoutServlet"><h2>Logout</h2></a>

</body>
</html>