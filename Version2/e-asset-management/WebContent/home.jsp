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
<% response.setHeader("Cache-Control","no-cache ,no-store, must-revalidate");
	   response.setHeader("Pragma","no-cache");
	   response.setHeader("Expires","0");
	   
	%> 
<div class="topnav">
  <a class="active" href="HomeStats">Home</a>
  <a href="FetchAllUsersServlet">Display Employees</a>
  <a href="GetAllForSearchServlet">Search For OverDues</a>
  <a href="AddAssetsServlet">Add</a>
  <a href ="Logout">Logout</a>
</div>
<br/><br/>
<div class = "main1">
<div class="box"><h2>Total number of users: ${listStat[0]}</h2></div>
<div class="box"><h2>Total assets issued: ${listStat[1]}</h2> </div>
<div class="box"><h2>Total users banned: ${listStat[2]} </h2></div>
</div>
<br/>
<br/>
<div class = "main2">
<div class="box"><h2>Total fine: ${listStat[3]} </h2></div>
<div class="box"><h2>Total assets due: ${listStat[4]} </h2></div>
<div class="box"><h2>Total assets returned today: ${listStat[5]}</h2></div>
</div>
</body>
</html>