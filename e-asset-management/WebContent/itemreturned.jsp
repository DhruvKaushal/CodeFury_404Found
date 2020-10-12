<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Item Return Success</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<link href="https://fonts.googleapis.com/css2?family=Sansita+Swashed:wght@600;700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@700&display=swap" rel="stylesheet">
</head>
<body>
	<div class="dash-back">
		<h2>Asset Return Page</h2>
		<a class="btn btn-primary stretched-link" href="employeedashboard.jsp">Back to Dashboard</a>
	</div>
	
	<h2>Your Item with ID: ${transId} has been returned successfully.</h2>
	<h2>Checkout your remaining items below:</h2>
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
			<core:forEach items="${allBorrowed}" var="al">
				<tr>
					<td>${al.assetName}</td>
					<td>${al.assetType}</td>
					<td>${al.assetDescription}</td>
					<td>${al.quantity}</td>
				</tr>
			</core:forEach>
		</tbody>
	</table>
	<hr />

</body>
</html>