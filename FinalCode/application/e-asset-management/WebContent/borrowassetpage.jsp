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
	
	<h3 style="margin-left:10px;">Search Results for ${currType} are:</h3>
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
			<tbody class="p-3 mb-2 bg-white text-dark">
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
		<h3 style="margin-left:10px;">Request to Borrow?</h3>
		<form action="BorrowServlet" method="post">
			<label class="label" for="assets">Choose an Asset ID:</label> 
			<select style="margin-right:10px;" class="btn btn-secondary active" name="assetid" id="assetid">
				<core:forEach items="${idList}" var="i">
					<option>${i}</option>
				</core:forEach>
			</select> <input class="submit-btn btn btn-danger btn-sm" type="submit"
				value="Borrow">
		</form>
	</div>
	<br><br><br><br>
	
</body>
</html>

<%@ include file = "css/footer.html" %>