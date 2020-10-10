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
<table class="overdue-table" style="width:85%;margin:auto">
<thead>
<tr class = "head-class">
        <th scope="col">ID</th>
        <th scope="col">UserName</th>
        <th scope="col">Name</th>
        <th scope="col">Phone No</th>
        <th scope="col">Email ID</th>
        <th scope="col">Last login date/time</th>
    </tr>
    </thead>
<core:forEach items="${userList}" var="u">
<tr>
<td scope="row"><a href="http://localhost:8081/Reg/FetchMoreDetails?id=${u.user_id}">${u.user_id}</a></td>
<td scope="row">${u.username}</td>
<td scope="row">${u.name}</td>
<td scope="row">${u.contactNo}</td>
<td scope="row">${u.email}</td>
<td scope="row">${u.login_date_and_time}</td>
</tr>
</core:forEach>
</table>

</body>
</html>



