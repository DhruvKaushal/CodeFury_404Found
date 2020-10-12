<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file = "css/headerfilelogin.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Borrower History</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<div style="display: flex;justify-content: flex-end;margin-right:5px;">
		<a class="btn btn-danger" style="align-items: center;height:42px;margin-top:10px;"  href="employeedashboard.jsp">Back to Dashboard</a>
	</div>
	<hr />
	
	<h2 style="font-size:30px;margin-top:2px;margin-left: 15px;">Checkout your borrowed items below:</h2>
	<div class="asset-list">
	<table class=" table table-hover table-info">
		<thead class="p-3 mb-2 bg-danger text-white">
				<tr>
					<th scope="col">Asset Name</th>
					<th scope="col">Asset Type</th>
					<th scope="col">Description</th>
					<th scope="col">Quantity Available</th>
				</tr>
		</thead>
		<tbody class="p-3 mb-2 bg-white text-dark">
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
	<hr />
	
	<div>
		<h3 style="margin-left: 10px;">Want to return your borrowed Assets?</h3>
		<form action="ReturnServlet" method="post">
			<label class="asset-req label" for="assets">Choose an Asset ID:</label> 
			<select style="width: 200px;margin-right:10px;" class="btn btn-secondary active" name="asset" id="asset">
				<core:forEach items="${allBorrowed}" var="a">
					<option>${a.transId}: ${a.assetName}</option>
				</core:forEach>
			</select> <input class="submit-btn btn btn-danger btn-sm" type="submit"
				value="Return">
		</form>
	</div>
	<br><br><br><br><br><br>
	
	<%@ include file = "css/footer.html" %>
</body>
</html>