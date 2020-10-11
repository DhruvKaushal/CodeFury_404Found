<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
<link rel="stylesheet" href="navbar.css">
</head>
<body>

<div class="topnav">
  <a class="active" href="HomeStats">Home</a>
  <a href="FetchAllUsersServlet">Display Employees</a>
  <a href="GetAllForSearchServlet">Search For OverDues</a>
  <a href="AddAssetsServlet">Add</a>
  <a href ="Logout">Logout</a>
</div>
<br/><br/>
<div class = "main">
<div class="box"><h2>The total Number of Users are : ${listStat[0]}</h2></div>
<div class="box"><h2>Total Assets issued : ${listStat[1]}</h2> </div>
<div class="box"><h2>Total Users Banned : ${listStat[2]} </h2></div>
<br/>
<br/>
<div class="box"><h2>Total Fine : ${listStat[3]} </h2></div>
<div class="box"><h2>Total Assets Due: ${listStat[4]} </h2></div>
<div class="box"><h2>Total Assets Returned Today : ${listStat[5]}</h2></div>
</div>
</body>
</html>