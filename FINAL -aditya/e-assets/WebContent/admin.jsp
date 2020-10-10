<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="addassets.css">

<title>Insert title here</title>
</head>
<body>
	<form name ="getCatgeory" method ="get" action = "AddAssetsServlet">  
		<button class="open-assets-button" type="submit" >Add</button>
   	</form>  
   	<form name ="search" method ="get" action = "GetAllForSearchServlet">  
   		<button class="open-search-button" type="submit">Overdue Assets</button>
   	</form>  
   	<form name ="searchUsers" method ="post" action = "FetchAllUsersServlet">  
   		<button class="open-users-button" type="submit">Users</button>
   	</form>  
   	
 	 		
</body>
</html>