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
		
		<form action="OrderSection2" method="post">
  			<label style="color:red" for="category">Choose a category:</label>
  				<select name="category">
  					<core:forEach items= "${category}" var="u">
  						<option value="${u}">${u}</option>
		       		 </core:forEach>
  				</select>
  				<br><br>
 				<input style="color:black" type="submit" name="selectedValue" value="FetchAsset">
		</form>
		
	</body>

</html>