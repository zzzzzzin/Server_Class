<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex04_include</title>
</head>
<body>
	<%--
		<%@ include file="URL" %>
		- 특정 JSP 페이지(HTML, Text 등)를 현재 JSP 페이지의 일부분으로 삽입하는 기술
		- 조각 페이지
	 --%>
	 <h1>커뮤니티</h1>
	 
	 <p>이런 기능</p>
	 <p>저런 기능</p>
	 <!--  
	 	로그인 부분 적용
	 	1. 클라이언드
	 	2. 서버
	 
	 -->
	 
	 <iframe src="login.jsp" frameborder="0"></iframe>
	 
	 <%@ include file="login.jsp" %>
	 
</body>
</html>