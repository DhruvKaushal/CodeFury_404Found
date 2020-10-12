<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Borrow Request Successful</title>
</head>
<body>
	<div style="display: flex; justify-content: space-between;">
		<h3 style="color: green;">${ans}</h3>
		<a style="margin-top: 20px;" href="employeedashboard.jsp">Back to Dashboard</a>
	</div>
	<hr />

	<h3>Updated Results for ${currType} are:</h3>
	<table class='table'>
		<thead class='thead'>
			<tr>
				<th>Asset ID</th>
				<th>Asset Name</th>
				<th>Description</th>
				<th>Quantity Available</th>
			</tr>
		</thead>
		<tbody class='tbody'>
			<core:forEach items="${assetList}" var="a">
				<tr>
					<td>${a.assetId}</td>
					<td>${a.assetName}</td>
					<td>${a.assetDescription}</td>
					<td>${a.quantity}</td>
				</tr>
			</core:forEach>
		</tbody>
	</table>
	<hr />
</body>
</html>