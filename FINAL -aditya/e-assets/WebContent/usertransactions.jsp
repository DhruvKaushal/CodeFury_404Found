<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="addassets.css">
</head>

</head>
<body>
<h1><b>Borrow History</b></h1>
<form action="FetchMoreDetailsServlet" action = "get">
<div class = "display-clickable-table">  	
<div class = "display-all" >  
	<table class="overdue-table" style="width:85%;margin:auto">
		<thead>
		<tr class = "head-class">
        <th scope="col">Order ID</th>
        <th scope="col">Asset ID</th>
        <th scope="col">Asset Name</th>
        <th scope="col">Asset Type</th>
        <th scope="col">Date issued</th>
        <th scope="col">Tentative return date</th>
        <th scope="col">Actual return date</th>
    	</tr>
    	</thead>
		<core:forEach items="${userTransactions}" var="u">
		<tr>
			<td scope="row">${u.transId}</td>
			<td scope="row">${u.assetId}</td>
			<td scope="row">${u.assetName}</td>
			<td scope="row">${u.assetType}</td>
			<td scope="row">${u.dateIssued}</td>
			<td scope="row">${u.tentativeReturn}</td>
			<td scope="row">${u.actualReturn}</td>
			
		</tr>	
		</core:forEach>
	</table>
</div>
</div>	
</form>		
</body>
</html>