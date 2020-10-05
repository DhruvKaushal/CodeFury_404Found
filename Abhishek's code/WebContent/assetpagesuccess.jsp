<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="assetpage.jsp"></jsp:include>
	<hr />
	
	<h3>Search Results for ${currType} are:</h3>
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
					<td>${a.name}</td>
					<td> ${a.desc}</td>
					<td> ${a.quantity}</td>
				</tr>
			</core:forEach>
		</tbody>
	</table>
	<hr />
	
	<p>Request to Borrow?</p>
	<form action="BorrowServlet" method="post">
		<label>
			AssetID: <input type="number" name="id">
		</label>
		<input type="submit" value="Borrow">
	</form>
</body>
</html>