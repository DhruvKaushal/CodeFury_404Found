<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Name</title>
</head>
<body>
            <form name = "SearchNameForm" action="SearchNameServlet" method = "post">
        	<label><b>Name</b></label><br />
                	<input type="text" placeholder="Enter User Name" name = "name" required> 
               <br />

                  <input type = "submit" value = "Search">  
               
        </form>
</body>
</html>