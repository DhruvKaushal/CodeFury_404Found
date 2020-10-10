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
<br />
<br />
<form name ="getCatgeory" method ="get" action = "AddAssetsServlet">  
<button class="open-assets-button" type="submit" >Add</button>
   	</form>  
 <br/>
 <br />
 <form name ="messageTable" method ="post" action = "GetMessageServlet">  
<button  type="submit" >Get Message</button>
   	</form>  	 		
</body>
</html>