<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
function selectedValue()
{  
	 

       var value = <%= request.getParameter("myselect") %>; 
       if(value!=null)  
        { 
    	   document.f1.category.selectedIndex=value;          

        }    

} 
</script>
</head>
<body>
	<jsp:include page="orderSection.jsp"></jsp:include>
	<input type = "button" onclick = "selectedValue()" value = "Display">
	<form action="OrderSecton" method="post"> 
  			<label style="color:red" for="assest" >Choose a product :</label>
  				<select name="assest">
  					<core:forEach items= "${assetlist}" var="up">
  						<option value="${up}">${up}</option>
		       		 </core:forEach>
  				</select>
  				<br><br>
 				<input type="submit" value="Submit">
	</form>
     </body>
</html>