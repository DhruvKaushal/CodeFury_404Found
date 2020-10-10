<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2>Hello.You have been registered.</h2>
<p>Id1: ${Employee.user_id}, Name : ${Employee.name}, Phone: ${Employee.contact}</p>
<jsp:include page="employeeLogin.jsp"></jsp:include>

</body>
</html>