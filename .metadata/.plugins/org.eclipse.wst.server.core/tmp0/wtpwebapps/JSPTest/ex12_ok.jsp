<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String width = request.getParameter("width");
	String height = request.getParameter("height");
	String color = request.getParameter("color");
	String txt1 = request.getParameter("txt1");
	String nums = request.getParameter("nums");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>
	
</style>
</head>
<body>
	<h1>결과</h1>
	<% for (int i=1; i<= Integer.parseInt(nums); i++) { %>
	<div style="width:<%= width%>px; height:<%= height%>px; background-color:<%= color%>;">
		<%= txt1 %>
	</div>
	<% } %>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
	</script>
</body>
</html>




