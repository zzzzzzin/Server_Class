<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
		클라이언트 -> 전송 데이터 -> 서버
		
		1. GET
			- URL 뒤에 붙여서 전송
			- ex) 택배 상자 겉의 송장에 데이터 추가
			- ex10_ok.jsp?txt=아무개&num=555
			- 쿼리 스트링(QueryString)
			- 톰캣을 사용해서 데이터 전송(UTF-8)
		
		2. POST
			- 패킷 본문(body)안에 넣어서 전송
			- ex) 택배 상자 안에 넣어서 전송
			- 자바를 사용해서 데이터 전송
	*/
	
	//데이터 수신하기
	//- String request.getParameter(String key)
	
	request.setCharacterEncoding("UTF-8"); //설정 바꾸기
	
	String txt = request.getParameter("txt");
	String num = request.getParameter("num");
	
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
	<!-- 전송된 데이터를 수신하는 페이지 -->
	
	<h1>결과</h1>
	<div>txt: <%= txt %></div>
	<div>num: <%= num %></div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
	</script>
</body>
</html>




