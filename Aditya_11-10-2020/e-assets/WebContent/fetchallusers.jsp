<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<!DOCTYPE html>
<html>
<head> 
<meta charset="ISO-8859-1">
  
    <link rel="stylesheet" 
href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" /> 

 <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script> 
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script> 
    <link rel="stylesheet" href="addassets.css">
 
</head> 
  
<body> 
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
        <th scope="col">UserName</th>
        <th scope="col">Name</th>
        <th scope="col">Phone No</th>
        <th scope="col">Email ID</th>
        <th scope="col">Last login date/time</th>
</tr>
</thead>
<core:forEach items="${userList}" var="u">
<tr class = "clickable-row" onclick="window.location='FetchMoreDetailsServlet?id=${u.userId}'">
<td scope="row">${u.userId}</td>
<td scope="row">${u.userName}</td>
<td scope="row">${u.name}</td>
<td scope="row">${u.contact}</td>
<td scope="row">${u.email}</td>
<td scope="row">${u.date}</td>
</tr>
</core:forEach>
</table>
 </div>
 </div>
  
   
</body> 
  
</html> 