<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="css/headerfilelogin.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile Page</title>
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="dash-back">
		<h2 style="padding: 7px;background-color:#dc3545; color:white;border: 5px solid black; font-size: 20px;margin-top:9px;margin-bottom: 0;">Your Profile Details:</h2>
		<a class="btn btn-danger" style="align-items: center;height:42px;margin-top:10px"  href="employeedashboard.jsp">Back to Dashboard</a>
	</div>
	<hr />

	<hr />
	<table class="table table-striped" style="width: 90%; margin: auto">
		<thead>
			<tr>
				<th scope="col">UserID</th>
				<th scope="col">UserName</th>
				<th scope="col">Contact No.</th>
				<th scope="col">Name</th>
				<th scope="col">Role</th>
				<th scope="col">Email</th>
				<th scope="col">Last Sign In on</th>
			</tr>
		</thead>
			<tr>
				<td scope="row">${employeeKey.employeeId}</td>
				<td scope="row">${employeeKey.employeeUsername}</td>
				<td scope="row">${employeeKey.employeeContact}</td>
				<td scope="row">${employeeKey.employeeName}</td>
				<td scope="row">${employeeKey.role}</td>
				<td scope="row">${employeeKey.employeeEmail}</td>
				<td scope="row">${employeeKey.signInDate}</td>
			</tr>
	</table>
	<br><br><br><br><br><br><br><br><br><br>

	<%@ include file="css/footer.html"%>
</body>
</html>