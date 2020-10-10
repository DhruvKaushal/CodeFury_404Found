<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
		<h1 style="background-color:powderblue">Id : ${borrowercopy.borrower_id}  Name : ${borrowercopy.borrower_name} E-mail: ${borrowercopy.borrower_email} </h1>
		
		<form action="myBorrowedItemList" method="post" >
			<label style="color:red">MyItemList:</label><br>
			<input type="submit" value="ok"><br>
		</form>
		<br>
		
		<form action="myMessage" method="post">
			<label style="color:blue">MyMessage:</label><br>
			<input type="submit" value="ok"><br>
		</form>
		<br>
		
		<form action="OrderSection" method="post">
			<label style="color:orange">OrderSection:</label><br>
			<input type="submit" value="ok"><br>
		</form>
	</body>
</html>