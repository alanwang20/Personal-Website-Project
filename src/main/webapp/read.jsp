<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String table = (String) request.getAttribute("table");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sci-Fi Library</title>
</head>
<body>
	<h1> Sci-Fi Library</h1>
	
	<%= table %>
	<a href="addform.html"> Add a Book</a>
</body>
</html>