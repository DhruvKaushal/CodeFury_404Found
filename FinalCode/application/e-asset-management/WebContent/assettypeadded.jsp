<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="addassets.css">
<link rel="stylesheet" href="navbar.css">

<title>Asset Type added</title>
</head>
<body>
<script src="admin.js"></script>
<div class="topnav">
  <a href="HomeStats">Home</a>
  <a href="FetchAllUsersServlet">Display Employees</a>
  <a href="GetAllForSearchServlet">Search for Overdues</a>
  <a class="active" href="AddAssetsServlet">Add</a>
  <a href ="Logout">Logout</a>
</div>
	<h3></h3>
  	<button class="open-type-button">Add Category</button>
 	<button class="open-assets-button">Add Assets</button>
    <div class="bg-modal2">
        <div class="modal-content">
        	<h1>Category Added!</h1><br />
        	<h3>Category Details:</h3>
        	<div class = "display-result">
        
        		<table>
  					<tr>
    					<th>Name</th>
    					<td>${assetTypeKey.typeName}</td>
  					</tr>
  					<tr>
    					<th>Lending Period (Days)</th>
    					<td>${assetTypeKey.lendingPeriod}</td>
  					</tr>
  					<tr>
    					<th>Fine Amount (Rs.)</th>
    					<td>${assetTypeKey.fine}</td>
  					</tr>
  					<tr>
    					<th>Ban Period (Days)</th>
    					<td>${assetTypeKey.ban}</td>
  					</tr>
				</table>
        	</div>
            <form method = "post" action="home.jsp">	
            	<button  class="close-button" type = "submit"><b>Close</b></button>                 	
        	</form>   
        </div>  
    </div> 

</body>
</html>

