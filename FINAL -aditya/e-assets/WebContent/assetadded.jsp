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
    <div class="bg-modal2">
        <div class="modal-content">
        	<h1>Asset Added!</h1><br />
        	<h3>Asset Details:</h3>
        	<div class = "display-result">
        
        		<table>
  					<tr>
    					<th>ID</th>
    					<td>${assetKey.assetId}</td>
  					</tr>
  					<tr>
    					<th>Name</th>
    					<td>${assetKey.assetName}</td>
  					</tr>
  					<tr>
    					<th>Category</th>
    					<td>${assetKey.assetType}</td>
  					</tr>
  					<tr>
    					<th>Quantity</th>
    					<td>${assetKey.quantity}</td>
  					</tr>
				</table>
        	</div>
          	
			<form method = "post" action="adminhome.jsp">	
            	<button  class="close-button" type = "submit"><b>Close</b></button>                 	
        	</form>   
        </div>  
    </div> 
    
    
</body>
</html>

