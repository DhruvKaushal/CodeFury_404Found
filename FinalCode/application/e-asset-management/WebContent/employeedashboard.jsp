<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file = "css/headerfilelogin.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Profile Page</title>
<link rel="stylesheet" href="/css/styles.css">
</head>
<style>
.row {
  display: flex;
}

.column {
  flex: 50%;
}
</style>
<body>
	<!-- Header Template Here -->

	<br/>
	<div class="row">
  		<div class="column"></div>
  		<div class="column"></div>
	</div>
	
	<h3 style="font-family: 'Sansita Swashed', cursive;margin-left: 10px;">Checkout the Assets Page right here:<a class="btn btn-danger my-2 my-sm-0" style="margin-left: 10px;"  href="AssetsPageServlet">Assets</a></h3>
	
	<br /><br /><br />
	<!-- User Current Assets Template Here -->
	<hr />
	
	<div>
		<h3 style="font-family: 'Sansita Swashed', cursive;margin-left: 10px;">Messages/Notifications from Admin</h3>
		<form action = "GetMessageServlet" method = "post">
		<button class="btn btn-danger" type ="submit">View Messages</button>
		</form>
		<br /><br /><br />
		<!-- Messages Template Here -->
	</div>
	<hr />
	
	<h3 style="font-family: 'Sansita Swashed', cursive;margin-left: 10px;">Return your Assets here:<a class="btn btn-danger my-2 my-sm-0" style="margin-left:10px;" href="BorrowedAssetsServlet">Return Asset here</a></h3>
	<!--  Footer Template Here	-->
	<br><br><br><br>
	
</body>
</html>
<%@ include file = "css/footer.html" %>


