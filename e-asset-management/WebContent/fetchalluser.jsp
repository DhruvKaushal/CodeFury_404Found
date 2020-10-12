<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<!DOCTYPE html>

<html>
<head> 
<meta charset="ISO-8859-1">
  <link rel="stylesheet" href="navbar.css">
  
    <link rel="stylesheet" 
href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" /> 

 <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script> 
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script> 
    <link rel="stylesheet" href="addassets.css">
 
</head> 
  
<body>

<div class="topnav">
  <a href="HomeStats">Home</a>
  <a class ="active" href="FetchAllUsersServlet">Display Employees</a>
  <a href="GetAllForSearchServlet">Search for Overdues</a>
  <a href="AddAssetsServlet">Add</a>
  <a href ="Logout">Logout</a>
</div>
<div class = "main">
<br>
<h1><b>All Users</b></h1><br>
<form action="FilterByBannedServlet" method="post">
	<select class = "filter-dropdown" name="filter">
		<option disabled="disabled">filter by</option>
		<option value="banned">Banned</option>
		<option value="notBanned">Not Banned</option>
		<option value="all">Show All</option>
		<input class="filer-btn" type="submit" value="filter">
	</select>
	
<p><b>  Click on a user to view borrow history.</b><p>	

</form>
<div class = "display-clickable-table">  	
<div class = "display-all-user" >   
<table class="clickable-table">
<thead>
<tr class = "head-class">
        <th scope="col">ID</th>
        <th scope="col">Username</th>
        <th scope="col">Name</th>
        <th scope="col">Phone No</th>
        <th scope="col">Email ID</th>
        <th scope="col">Last login date/time</th>
</tr>
</thead>
<core:forEach items="${userList}" var="u">
<tr class = "clickable-row" onclick="window.location='FetchMoreDetailsServlet?id=${u.employeeId}'">
<td scope="row">${u.employeeId}</td>
<td scope="row">${u.employeeUsername}</td>
<td scope="row">${u.employeeName}</td>
<td scope="row">${u.employeeContact}</td>
<td scope="row">${u.employeeEmail}</td>
<td scope="row">${u.signInDate}</td>
</tr>
</core:forEach>
</table>
 </div>
 </div>
  </div>
   
</body> 
  
</html> 