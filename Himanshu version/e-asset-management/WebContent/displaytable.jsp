<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
    
 <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="addassets.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<script src="admin.js"></script>
<table id="UserTable">
 <tr>
    <th>Order Id</th>
    <th>User Id</th>
    <th>User Name</th>
    <th>Asset Id</th>
    <th>Asset Type</th>
    <th>Asset Name</th>
    <th>Date Issued</th>
    <th>Tentative Return Date</th>
    <th>Actual Return Date</th>
    <th>Message User</th>
  </tr>
 
  	<core:forEach items="${listOrder}" var="order">
  	 <tr>
        		<td>${order.orderId}</td>
        		<td>${order.userId}</td>
        		<td>${order.userName }</td>
        		<td>${order.assetId}</td>
        		<td>${order.itemType}</td>
        		<td>${order.assetName}</td>
        		<td>${order.dateIssue}</td>
        		<td>${order.tentativeReturn}</td>
        		<td>${order.actualReturn}</td>
        		<td><button onclick="moveValue(this)">
          		<span class="glyphicon glyphicon-envelope" ></span>
        		</button></td>
        		</tr> 
  				</core:forEach>    
	
  </table>
  
   <div class="bg-modal2" id="myForm">
        	<div class="modal-content2">
    
            <div class="close" onclick="closeForm()">+</div>
            
    
            <form  name = "addAssetTypeForm" action="SendMessage" method = "post" >
            			<label>Order Id: </label>
                       <input type='text'  id ="messageOrder" name = "messageOrder" value="" readonly="readonly" />
						<br/>
						<br/>
						<label>Add Message:</label>
						<textarea id="message" name="message" rows="4" cols="38" required></textarea>
                		<br/>
                		<br/>
                  <button type="submit" class="btn"><b>Send Messages</b></button>    			                 	
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