<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="addassets.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<script src="admin.js"></script>

<table id="messageTable">
 <tr>
    <th>Message Id</th>
    <th>Message</th>
    <th>Order Id</th>
    <th>Delete</th>
  </tr>
 
  	<core:forEach items="${messageList}" var="message">
  	 <tr>
        		<td>${message.messageId}</td>
        		<td>${message.message_content}</td>
        		<td>${message.orderId }</td>
        		<td><button onclick= "moveValue(this)">
          		<span class="glyphicon glyphicon-remove" ></span>
        		</button></td>
        		</tr> 
  				</core:forEach>    
	
  </table>
   <div class="bg-modal3" id="myForm">
        	<div class="modal-content3">
    
            <div class="close" onclick="closeForm()">+</div>
            
    
						<form  name = "myForm" action="UpdateMessage" method = "post" >
            			<label>Are you sure you want to Delete it ?</label>
                		<br/>
                		<br/>                		
                		<input type="hidden"  id ="messageOrder" name = "messageOrder" value="" />
						<div class="btn-group" role="group" >
						  	<button type="submit" class="btn btn-primary">Submit</button>
						  	<button type="button" class="btn btn-danger"  onclick="closeForm()">Cnacel</button>
						</div>
                </form>
        </div>  
    </div> 
<script> 
    function moveValue(el)
    {
       	var n = el.parentNode.parentNode.cells[0].textContent;
       	document.getElementById('messageOrder').value = n;
		openForm();
    }
  </script> 
</body>
</html>