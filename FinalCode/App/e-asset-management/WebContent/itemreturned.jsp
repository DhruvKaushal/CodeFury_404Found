<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
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
	<div style="display: flex;justify-content: flex-end;margin-right:5px;">
		<a class="btn btn-danger" style="align-items: center;height:42px;margin-top:10px;"  href="employeedashboard.jsp">Back to Dashboard</a>
	</div>
	<hr>
	<h2 style="margin-top:9px;margin-left: 10px;">Your Item with ID: ${transId} has been returned successfully.</h2>
	<br>
	
	<h2 style="margin-left:10px;">Checkout your remaining items below:</h2>
	
	<div class=asset-list>	
	<table class="table table-striped">
		<thead class='thead'>
			<tr>
				<th scope="col">Asset Name</th>
				<th scope="col">Asset Type</th>
				<th scope="col">Description</th>
				<th scope="col">Quantity Available</th>
			</tr>
		</thead>
		<tbody class='tbody'>
			<core:forEach items="${allBorrowed}" var="al">
				<tr>
					<td scope="row">${al.assetName}</td>
					<td scope="row">${al.typeName}</td>
					<td scope="row">${al.assetDescription}</td>
					<td scope="row">${al.quantity}</td>
				</tr>
			</core:forEach>
		</tbody>
	</table>
	</div>
	
	<br><br><br><br><br><br><br><br>
	
	<%@ include file = "css/footer.html" %>
</body>
</html>

