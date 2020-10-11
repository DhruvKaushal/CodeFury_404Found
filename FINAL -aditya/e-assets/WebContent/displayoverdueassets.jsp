<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="addassets.css">
</head>
<body>
<jsp:include page="searchall.jsp"></jsp:include>
<div class = "display-all-modal" id="myTable">  	
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
 
  </tr>
  
    
    
    <core:forEach items="${orderList}" var="orderList">
    	<tr>
    	<td>${orderList.transId}</td>
    	<td>${orderList.userId}</td>
    	<td>${orderList.userName}</td>
    	<td>${orderList.assetId}</td>
    	<td>${orderList.assetName}</td>
    	<td>${orderList.assetType}</td>
    	<td>${orderList.dateIssued}</td>
    	<td>${orderList.tentativeReturn}</td>
    	<td>${orderList.actualReturn}</td>
    	<td>${orderList.fine}</td>
    	</tr>
    </core:forEach>
  
</table>
</div>
</div>    
<script>

	document.getElementById("myTable").style.display = "block";

</script>


</body>
</html>