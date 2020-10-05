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

	  <h3>Admin Home</h3>
    
  	<button class="open-type-button" onclick="openForm1()">Add Category</button>
 	<button class="open-assets-button" onclick="openForm2()">Add Assets</button>

    <div class="bg-modal" id="myForm2">
        	<div class="modal-content">
    
            <div class="close" onclick="closeForm2()">+</div>
            
    
            <form action="AddAssetsServlet" method = "post">
            	<h1>Add Assets</h1>
            	<label><b>Name</b></label><br />
                	<input type="text" placeholder="Enter Asset Name" name = "name" required>
                
                	
                <label><b>Category</b></label><br />
                	<input type="text" placeholder="Enter Category" name = "category">
                
                
                <label><b>Description</b></label><br />
                	<input type="text" placeholder="Enter Description" name = "description" required>
                	
                 <label><b>Quantity</b></label><br />
                	<input type="text" placeholder="Enter Quantity" name = "quantity">	
                
                  <button type="submit" class="btn"><b>Add</b></button>    			                 	
        	</form> 
    
        </div>  
    </div> 
    

    <div class="bg-modal" id="myForm1">
        	<div class="modal-content">
    
            <div class="close" onclick="closeForm1()">+</div>
            
    
            <form action="AddAssetTypeServlet" method = "post">
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
    
    
   
	<script>
	function openForm1() {
  		document.getElementById("myForm1").style.display = "flex";
	}

	function closeForm1() {
  	document.getElementById("myForm1").style.display = "none";
	}
	
	function openForm2() {
  		document.getElementById("myForm2").style.display = "flex";
	}

	function closeForm2() {
  	document.getElementById("myForm2").style.display = "none";
	}
	</script>

</body>
</html>

