<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//1. 인증 티켓 제거
	//2. 돌아가기
	
	//session.removeAttribute("id");
	//session.removeAttribute("name");
	//session.removeAttribute("lv");
	
	session.invalidate();

	response.sendRedirect("/auth/index.jsp");

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>
	
</style>
</head>
<body>
	<!--  -->
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
	</script>
</body>
</html>










