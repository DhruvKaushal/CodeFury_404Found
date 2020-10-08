<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="addassets.css">
<title>Insert title here</title>
</head>
<body>
	<script src="admin.js"></script>		
	<h3>Admin Home</h3>
    
  	<button class="open-type-button" onclick="openForm1()">Add Category</button>
  	<form  method ="get" action = "AddAssetsServlet"  >
 		<button class="open-assets-button" type="submit" onclick="openForm2()">Add Assets</button>
  	</form>
 	
 	<button class="open-name-button" onclick="location.href='searchName.jsp'" >Search By Name</button>

<div class="bg-modal" id="myForm2">
         <div class="modal-content">
            <div class="close" onclick="closeForm2()">+</div>
            
    
            <form name = "addAssetForm"action="AddAssetsServlet" method = "post" onsubmit = "return validateForm2()">
            	<h1>Add Assets</h1>
            	<label><b>Name</b></label><br />
                	<input type="text" placeholder="Enter Asset Name" name = "name" required>
                <label><b>Category</b></label>
                <br />
                	<select name="category">
    					<core:forEach items="${listCategory}" var="category">
        					<option value="${category}">${category}</option>
    					</core:forEach>
				</select>
               <br />
               <br />
                	
                <label><b>Description</b></label><br />
                	<input type="text" placeholder="Enter Description" name = "description" required>
                	
                 <label><b>Quantity</b></label><br />
                	<input type="text" placeholder="Enter Quantity" name = "quantity" required>	
                
                  <button type="submit" class="btn"><b>Add</b></button>    			                 	
        	</form> 
    
        </div>  
    </div> 
   

    <div class="bg-modal" id="myForm1">
        	<div class="modal-content">
    
            <div class="close" onclick="closeForm1()">+</div>
            
    
            <form  name = "addAssetTypeForm" action="AddAssetTypeServlet" method = "post" onsubmit = "return validateForm1()">
            	<h1>Add Category</h1>
            	<label><b>Category Name</b></label><br />
                	<input type="text" placeholder="Enter Category Name" name = "type" required>
                             
                <label><b>Lending Period</b></label><br />
                	<input type="text" placeholder="Enter Lending Period (Days)" name = "borrow" required>
                	
                 <label><b>Fine</b></label><br />
                	<input type="text" placeholder="Enter Fine (Rupees)" name = "fine" required>	
                	
                <label><b>Ban Period</b></label><br />
                	<input type="text" placeholder="Enter Ban Period (Days)" name = "ban" required>		
                
                  <button type="submit" class="btn"><b>Add</b></button>    			                 	
        	</form> 
    
        </div>  
    </div> 


</body>
</html>

