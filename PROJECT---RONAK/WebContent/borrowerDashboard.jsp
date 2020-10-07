<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
	    <h1>Id : ${usercopy.user_id}  Name : ${usercopy.name} E-mail: ${usercopy.email} </h1>
		<form action="myBorrowedItemList" method="post">
			<label>MyItemList:</label><br>
			<input type="submit" value="ok"><br>
		</form>
		<br>
		<form action="myMessage" method="post">
			<label>MyMessage:</label><br>
			<input type="submit" value="ok"><br>
		</form>
		<br>
		<form action="orderSection" method="post">
			<label>OrderSection:</label><br>
			<input type="submit" value="ok"><br>
		</form>
		
	</body>
</html>