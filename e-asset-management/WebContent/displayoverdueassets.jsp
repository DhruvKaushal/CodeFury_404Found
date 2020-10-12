<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="navbar.css">
<link rel="stylesheet" href="addassets.css">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>OverDue List</title>
<link rel="stylesheet" href="addassets.css">
</head>
<body>
<script src="admin.js"></script>
<div class="topnav">
  <a href="HomeStats">Home</a>
  <a href="FetchAllUsersServlet">Display Employees</a>
  <a class="active" href="GetAllForSearchServlet">Search For OverDues</a>
  <a href="AddAssetsServlet">Add</a>
  <a href ="Logout">Logout</a>
</div>
<div class = "display-all-modal-overdue" id="myTable">  	
<div class = "display-all" >
<h2>Overdue Asset Details</h2>
        
<table class = "overdue-table">
  <tr class = "head-class">
    <th>Order Id</th>
    <th>User Id</th>
    <th>Name</th>
    <th>Asset Id</th>
    <th>Asset Name</th>
    <th>Asset Type</th>
    <th>Date of Issue</th>
    <th>Tentative Return Date</th>
    <th>Actual Return Date</th>
    <th>Fine</th>
    <th>Return Status</th>
    <th>Send Message</th>        
  </tr>
  
    
    
    <core:forEach items="${orderList}" var="orderList">
    	<tr>
    	<td>${orderList.transId}</td>
    	<td>${orderList.empId}</td>
    	<td>${orderList.userName}</td>
    	<td>${orderList.assetId}</td>
    	<td>${orderList.assetName}</td>
    	<td>${orderList.assetType}</td>
    	<td>${orderList.dateIssued}</td>
    	<td>${orderList.tentativeReturn}</td>
    	<td>${orderList.actualReturn}</td>
    	<td>${orderList.fine}</td>
    	<td>${orderList.isReturn? 'Yes' : 'No'}</td>
    	<td><button onclick="moveValue(this)">
          		<span class="glyphicon glyphicon-envelope" ></span>
        		</button></td>
    	</tr>
    </core:forEach>
    </table>
</div>
</div>   
       <div class="bg-modal3" id="myForm3">
        	<div class="modal-content3">
    
            <div class="close" onclick="closeForm()">+</div>
            
    
            <form  name = "addAssetTypeForm" action="SendMessage" method = "post" >
            			<label>Order Id: </label>
                       <input type='text'  id ="messageOrder" name = "messageOrder" value="" readonly="readonly" />
						<br/>
						<br/>
						<label>Add Message:</label><br>
						<textarea id="message" name="message" rows="4" cols="55" required></textarea>
                		<br/>
                		<br/>
                  <button type="submit" class="btn"><b>Send Message</b></button>    			                 	
        	</form> 
    
        </div>  
    </div> 
<script>

	
	 function moveValue(el)
	    {
	      	var n = el.parentNode.parentNode.cells[0].textContent;
	      	document.getElementById('messageOrder').placeholder = n;
	      	document.getElementById('messageOrder').value = n;
			openForm();
	    }

</script>


</body>
</html>