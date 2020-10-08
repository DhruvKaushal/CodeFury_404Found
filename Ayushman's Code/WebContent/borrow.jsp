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

<h2>Please select a Category.</h2>
<form action="OrderItem" method="post">
    Select a Category:&nbsp;
    <select name="category">
        <core:forEach items="${listCategory}" var="category">
            <option value="${category}">${category}</option>
        </core:forEach >
    </select>
    <br/><br/>
    <input type="submit" value="Submit" />
</form>


<h2>After selecting category.Please select an asset if available.</h2>
<form action="OrderItem" method="get">
    Select an Item:&nbsp;
    <select name="item">
        <core:forEach items="${itemCategory}" var="item">
            <option value="${item.item_type}&${item.item_id}">${item}</option>
        </core:forEach >
    </select>
    <br/><br/>
    <input type="submit" value="Submit" />
</form>

</body>
</html>