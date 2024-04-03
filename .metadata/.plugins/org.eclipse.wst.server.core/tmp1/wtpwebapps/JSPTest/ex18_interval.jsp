<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h1>세션 만료시간 설정하기</h1>
	<%
		session.setMaxInactiveInterval(30);
		/*  
			1. 로그인
			 - 개인정보
			 - 페이지 이동 -> 유지
			 - 접속 해제 -> 유지 불필요
			 
			 2. 장바구니
			 	- 개인 정보
			 	- 페이지 이동
			 	- 접속 해제 -> 유지 불필요
			
		*/
	%>
	
	<div> <a href="ex18_session.jsp">돌아가기</a></div>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
	</script>
</body>
</html>




