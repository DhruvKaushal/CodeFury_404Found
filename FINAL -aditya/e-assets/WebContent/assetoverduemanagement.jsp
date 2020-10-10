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


	<script src="admin.js"></script>		
	<h3>Asset overdue</h3>
    
  					<!-- <label><b>Search By Date</b></label><br />
                	
                	<select class = "dropdown" name = "name">
        			<option value = "hello">Name</option>
        			</select>	
                	<label><b>Search By Name</b></label><br />
                	
                	<select class = "dropdown" name = "name">
        			<option value = "hello">Name</option>
        			</select> -->
        			<!-- <label><b>Search By Category</b></label><br /> -->
        			<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
        			
        			<form action="FetchCategoryServlet">
        				<label for="category">Choose an asset category:</label>
                		<select class = "dropdown" name = "assets" id="asset">
                			<core:forEach items="${categoryList}" var="c">
                				<option >${c}</option>
                				</core:forEach>      		
        				</select>
        				
        				 <button type="submit" class="btn"><b>Search</b></button>
        			</form>
        			
                	
                
               
    



</body>
</html>

