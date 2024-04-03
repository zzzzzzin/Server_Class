<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//자바 영역 > 변수 선언 > 변수 종류?
	//서블릿 == JSP
	int a = 10; //지역 변수
%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello</h1>
	<p>안녕하세요~</p>
	<div>a: <%= a %></div>
</body>
</html>