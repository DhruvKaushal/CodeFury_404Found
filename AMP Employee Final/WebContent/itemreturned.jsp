<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Item Return Success</title>
</head>
<body>

<h2> Your Item with ID: ${transId} has been returned successfully.</h2>

<h3>Checkout your remaining items right here:</h3>
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
			<core:forEach items="${allLinked}" var="al">
				<tr>
					<td>${al.assetName}</td>
					<td>${al.assetType}</td>
					<td> ${al.assetDescription}</td>
					<td> ${al.quantity}</td>
				</tr>
			</core:forEach>
		</tbody>
	</table>

</body>
</html>