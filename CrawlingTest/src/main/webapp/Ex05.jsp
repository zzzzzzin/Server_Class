<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<!-- 
		페이지 렌더링
		- 페이지 소스를 누가 주체가 되어 생성하는지?
		
		1. SSR, Server side rendering
			- Servlet/JSP
			
		2. CSR, Client side rendering
			- JavaScript
	-->
	<h1>주소록</h1>
	
	<ul id="list1">
		<li>홍길동</li>
		<li>아무개</li>
		<li>하하하</li>
		<li>강아지</li>
		<li>고양이</li>
	</ul>
	
	<hr>
	
	<ul id="list2"></ul>
	
	<hr>
	<div><input type="text" id="txt1"></div>
	<div><input type="button" value="버튼" id="btn1"></div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	
		$('#btn1').click(function() {
			alert(new Date());
		});
	
		const list2 = ['사과', '바나나', '딸기', '파인애플', '망고'];
		
		list2.forEach((item) => {
			$('#list2').append('<li>' + item + '</li>');
		});
	</script>
</body>
</html>










