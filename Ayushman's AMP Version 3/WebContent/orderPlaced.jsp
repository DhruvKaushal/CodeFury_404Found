<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Thank You for placing the order.Please return the product on time</h1>
<p>Id: ${EmployeeDetails.user_id}, Name : ${EmployeeDetails.name}, Phone: ${EmployeeDetails.contact}</p>
<p>Product Details:${itemDetails.item_type},Item Id:${itemDetails.item_id}</p>

</body>
</html>