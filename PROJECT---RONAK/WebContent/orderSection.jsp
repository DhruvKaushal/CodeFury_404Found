<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
		
		<form action="orderSection" method="post">
  			<label for="category">Choose a category:</label>
  				<select name="category">
  					<core:forEach items= "${categorylist}" var="u">
  						<option value="${u}">${u}</option>
		       		 </core:forEach>
  				</select>
  				<br><br>
 				<input type="submit" value="Submit">
		</form>
		
		<form action="orderSection" method="get"> 
  			<label for="assest" >Choose a product :</label>
  				<select name="assest">
  					<core:forEach items= "${assestlist}" var="up">
  						<option value="${up}">${up}</option>
		       		 </core:forEach>
  				</select>
  				<br><br>
 				<input type="submit" value="Submit">
		</form>
	</body>
</html>