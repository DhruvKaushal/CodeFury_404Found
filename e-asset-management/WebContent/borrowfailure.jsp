<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Borrow Request Failed</title>
</head>
<body>
	<jsp:include page="borrowassetpage.jsp"></jsp:include>
	<h3 style="color: red;">${err}</h3>
</body>
</html>