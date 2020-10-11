<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Name</title>
  <link rel="stylesheet" href="navbar.css">
  <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="addassets.css">
</head>
<body>
<div class="topnav">
  <a href="HomeStats">Home</a>
  <a href="FetchAllUsersServlet">Display Employees</a>
  <a class ="active" href="GetAllForSearchServlet">Search for Overdues</a>
  <a href="AddAssetsServlet">Add</a>
  <a href ="Logout">Logout</a>
</div>
<div class = "main">
<br>
         <form action="GetOverdueAssetsServlet" method = "post">
            	<h1><b>Search for Overdue Assets</b></h1>
            	<label class = "label-for-search"><b>Category</b></label>
                	
                <select class = "input-for-search" name="type">
                	<option class = "input-for-search" value="none" selected disabled hidden>Select an Option</option>
    					<core:forEach items="${listCategory}" var="category">
        					<option value="${category}">${category}</option>
    					</core:forEach>
				</select>
				<label class = "label-for-search"><b>Name</b></label>
				
				<datalist id="list">
				<core:forEach items="${listName}" var="name">
        					<option value="${name}">${name}</option>
    					</core:forEach>
				</datalist>
				<input class = "input-for-search" type="text" list="list" name="name" maxlength="50" autocomplete="off" spellcheck="off" placeholder="Type Name"> <br>
				
				<label class = "label-for-search"><b>Date</b></label>
				
				    <input type="text" id="datepicker" class = "datepicker" width="276" placeholder = "Date" name="date" >
    				<label class = "subtitle-date">(Displays details of assets with date of issue < Date)</label>
    				<script>
        				$('#datepicker').datepicker({
           			 	uiLibrary: 'bootstrap4', format: 'yyyy-mm-dd'
            			});
            
    				</script>
				
                <input type = "submit"  class="search-btn"  onclick="openTable()" value = "Search">
                  		                 	
</form> 
</div>
</body>
</html>