<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Assets List from Category</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<jsp:include page="selectassetpage.jsp"></jsp:include>
	<hr />

	<div class="asset-list">
		<h3>Search Results for ${currType} are:</h3>
		<table class="table table-hover table-info">
			<thead class='table-dark'>
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
	</div>
	<hr />

	<div>
		<h3 class="asset-req">Request to Borrow?</h3>
		<form action="BorrowServlet" method="post">
			<label class="label" for="assets">Choose an Asset ID:</label> <select
				class="btn btn-primary active" name="assetid" id="assetid">
				<core:forEach items="${idList}" var="i">
					<option>${i}</option>
				</core:forEach>
			</select> <input class="submit-btn btn btn-primary btn-sm" type="submit"
				value="Borrow">
		</form>
	</div>
</body>
</html>