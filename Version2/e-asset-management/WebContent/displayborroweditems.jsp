<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Borrower History</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<a class="btn btn-primary stretched-link" href="employeedashboard.jsp">Back to Dashboard</a>
	<hr />
	
	<h2>Checkout your borrowed items below:</h2>
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
					<td>${al.typeName}</td>
					<td>${al.assetDescription}</td>
					<td>${al.quantity}</td>
				</tr>
			</core:forEach>
		</tbody>
	</table>
	<hr />
	
	<div>
		<h3 class="asset-req">Return your Borrowed Assets?</h3>
		<form action="ReturnServlet" method="post">
			<label class="label" for="assets">Choose an Asset ID:</label> <select
				class="btn btn-primary active" name="asset" id="asset">
				<core:forEach items="${allBorrowed}" var="a">
					<option>${a.transId}: ${a.assetName}</option>
				</core:forEach>
			</select> <input class="submit-btn btn btn-primary btn-sm" type="submit"
				value="Return">
		</form>
	</div>
	
</body>
</html>