<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file = "css/headerfilelogin.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Borrow Request Successful</title>
</head>
<body>

	<div style="font-size:20px;display: flex;justify-content: space-between;font-family: 'Sansita Swashed', cursive;margin-top: 10px;margin-left: 10px;">
		<h3 style="font-size: 20px;margin:20px 0 0 5px;">Updated Results for ${currType} are:</h3>
		<a class="btn btn-danger" style="align-items: center;height:42px;margin: 10px 5px 0 0;" href="employeedashboard.jsp">Back to Dashboard</a>
	</div>
	
	<hr />
	<h3>Your order is successful</h3>
	
	<div class="asset-list">
	<table class="table table-hover table-info">
		<thead class="p-3 mb-2 bg-danger text-white">
			<tr>
				<th>Asset ID</th>
				<th>Asset Name</th>
				<th>Description</th>
				<th>Quantity Available</th>
			</tr>
		</thead>
		<tbody class='p-3 mb-2 bg-white text-dark'>
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
	</div>
	<br><br><br><br>
	
	<%@ include file = "css/footer.html" %>
</body>
</html>

