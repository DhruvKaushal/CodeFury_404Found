<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Popular Items</title>
</head>
<body>
	<!-- Header Template Here-->
	<a href="loginsuccess.jsp">Back to Dashboard</a>
	<br />
	
<%-- 	<h3>Checkout Popular Items right here:</h3>
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
			<core:forEach items="${popularList}" var="p">
				<tr>
					<td>${p.name}</td>
					<td>${p.type}</td>
					<td> ${p.desc}</td>
					<td> ${p.quantity}</td>
				</tr>
			</core:forEach>
		</tbody>
	</table> --%>
	<hr />
	
	<h2>Request a New Asset:</h2>
	<form action="FetchAssetsServlet">
  		<label for="category">Choose an asset category:</label>
  		<select name="assets" id="asset">
    		<core:forEach items="${categoryList}" var="c">
				<option>${c}</option>
			</core:forEach>
  		</select>
  		<input type="submit" value="Fetch Assets">
	</form>
	<br />
	
	<!--  Footer Template Here-->
</body>
</html>