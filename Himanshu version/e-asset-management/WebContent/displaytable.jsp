<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
    
 <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="addassets.css">

<title>Insert title here</title>
</head>
<body>
<table id="UserTable">
 <tr>
    <th>Order Id</th>
    <th>User Id</th>
    <th>User Name</th>
    <th>Asset Id</th>
    <th>Asset Type</th>
    <th>Asset Name</th>
    <th>Date Issued</th>
    <th>Tentative Return Date</th>
    <th>Actual Return Date</th>
    <th>Message User</th>
  </tr>
 
  	<core:forEach items="${listOrder}" var="order">
  	 <tr>
        		<td>${order.orderId}</td>
        		<td>${order.userId}</td>
        		<td>${order.userName }</td>
        		<td>${order.assetId}</td>
        		<td>${order.itemType}</td>
        		<td>${order.assetName}</td>
        		<td>${order.dateIssue}</td>
        		<td>${order.tentativeReturn}</td>
        		<td>${order.actualReturn}</td>
        		<td><button type="button" class="btn btn-primary btn-xs">Message</button>
</td>
        		  	 </tr> 
  				</core:forEach>    
	
  </table>
</body>
</html>