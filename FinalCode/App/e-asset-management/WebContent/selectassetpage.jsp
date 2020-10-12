<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file = "css/headerfilelogin.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Popular Items</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
	<!-- Header Template Here-->
	
	<div class="dash-back">
		<h2 style="padding: 7px;background-color:#dc3545; color:white;border: 5px solid black; font-size: 20px;margin-top:9px;margin-bottom: 0;">Asset Request Page</h2>
		<a class="btn btn-danger" style="align-items: center;height:42px;margin-top:10px"  href="employeedashboard.jsp">Back to Dashboard</a>
	</div>
	<hr />
	
	<div>
		<h2>Request a New Asset:</h2>
		<form action="FetchAssetsServlet">
  			<label class="label" for="category">Choose an asset category:</label>
  			<select style="margin-left:5px;" class="btn btn-secondary active" name="assets" id="assets">
    			<core:forEach items="${categoryList}" var="c">
					<option>${c}</option>
				</core:forEach>
  			</select>
  			<input class="submit-btn btn btn-danger btn-sm" type="submit" value="Fetch Assets">
		</form>
	</div>
	<!--  Footer Template Here-->
</body>
</html>

