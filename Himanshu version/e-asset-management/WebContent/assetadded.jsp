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
  	<button class="open-type-button">Add Category</button>
 	<button class="open-assets-button">Add Assets</button>
    <div class="bg-modal2" id="myForm2">
        <div class="modal-content">
           <h1>Asset Added!</h1>
           <p>Id: ${assetKey.assetId}, Name : ${assetKey.assetName}, Type: ${assetKey.assetType}, Quantity: ${assetKey.quantity}</p>
            <form method = "post" action="adminhome.jsp">	
            	<button  class="close-button" type = "submit"><b>Close</b></button>                 	
        	</form>   
        </div>  
    </div> 
    
</body>
</html>

