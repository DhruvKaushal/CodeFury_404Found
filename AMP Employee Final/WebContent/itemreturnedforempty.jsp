<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file = "css/headerfilelogin.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Item Return Success</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
	<div class="dash-back" style="text-align: center;">
		<a class="btn btn-danger" style="align-items: center;height:42px;margin-top:5px"  href="employeedashboard.jsp">Back to Dashboard</a>
	</div>
	<hr>
	
	<h2 style="font-size: 20px;margin-top:15px;margin-bottom: 0;">Your Item with ID: ${transId} has been returned successfully.</h2>
	<br><br>
	<h2 class="dash-back" style="margin-left:10px;">You have no remaining items right now.</h2>
	<br><br><br><br><br><br><br><br><br><br><br><br><br>
	
	<%@ include file = "css/footer.html" %>
</body>
</html>