<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Popular Items</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<link href="https://fonts.googleapis.com/css2?family=Sansita+Swashed:wght@600;700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@700&display=swap" rel="stylesheet">
</head>
<body>
	<!-- Header Template Here-->
	<div class="dash-back">
		<h2>Asset Request Page</h2>
		<a class="btn btn-primary stretched-link" href="employeedashboard.jsp">Back to Dashboard</a>
	</div>
	
<%-- 	<h3>Checkout Popular Items right here:</h3>
	<table class='table'>
		<thead class='thead'>
				<tr>
					<th>Asset Name</th>
					<th>Asset Type</th>
					<th>Description</th>
					<th>Quantity Available</th>
				</tr>
		</thead>
		<tbody class='tbody'>
			<core:forEach items="${popularList}" var="p">
				<tr>
					<td>${p.name}</td>
					<td>${p.type}</td>
					<td> ${p.desc}</td>
					<td> ${p.quantity}</td>
				</tr>
			</core:forEach>
		</tbody>
	</table> --%>
	<hr />
	
	<div>
		<h2 class="asset-req" >Request a New Asset:</h2>
		<form action="FetchAssetsServlet">
  			<label class="label" for="category">Choose an asset category:</label>
  			<select class="btn btn-primary active" name="assets" id="assets">
    			<core:forEach items="${categoryList}" var="c">
					<option>${c}</option>
				</core:forEach>
  			</select>
  			<input class="submit-btn btn btn-primary btn-sm" type="submit" value="Fetch Assets">
		</form>
	</div>
	
	<!--  Footer Template Here-->
</body>
</html>