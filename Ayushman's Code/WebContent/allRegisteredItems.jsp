<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
</head>
<body>

Please select item to return.

<h2>Please select a Product.</h2>
<form action="ReturnServlet" method="post">
    Select a Category:&nbsp;
    <select name="category">
        <core:forEach items="${RegisteredItems}" var="category">
            <option value="${category.order_id}">${category}</option>
        </core:forEach >
    </select>
    <br/><br/>
    <input type="submit" value="Submit" />
</form>

</body>
</html>