<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Profile Page</title>
<link rel="stylesheet" href="/css/styles.css">
</head>
<body>
	<!-- Header Template Here -->
	
	<div style="display: flex;justify-content: space-between;">
		<h3 style="color: green;">Hello, ${userKey.userName} </h3>
		<div>
			<h3><a href="profile.jsp">Profile Details</a></h3>
			<h3><a href="LogoutServlet">Logout</a></h3>
		</div>
	</div>
	<hr/>
	
	<h3>Checkout the Assets Page right here:<a href="PopularAssetServlet">Assets</a></h3>
	<hr />
	
	<h3>Want to leave?</h3>
	<h4>Delete User Profile: <a href="deleteuser.jsp">Delete User</a></h4>
	
	<!--  Footer Template Here	-->
</body>
</html>