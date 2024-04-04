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
	<h1>쿠키<small>Cookie</small></h1>
	
	<div>
		<input type="button" value="쿠키에 데이터 저장하기" id="btn1">
		<input type="button" value="쿠키의 데이터 읽기" id="btn2">
	</div>
	
	<div>
		<a href="ex24_cookie_1.jsp">이동하기</a>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="/jsp/js/cookie.js"></script>
	<script>
		$('#btn1').click(function() {
			//쿠키 쓰기
			//console.log(document.cookie); //아무것도 안 나옴 -> 아직 쿠키가 없음
			//console.log(typeof document.cookie); //string -> 쿠키는 string형태로 저장
			
			//document.cookie = '홍길동,100,90,80'; //쿠키 생성
			//document.cookie = 'name=홍길동&kor=100&eng=90&math=80'; //쿠키 생성
			
			setCookie('name', '홍길동');
			setCookie('kor', '100');
			setCookie('eng', '90');
			setCookie('math', '80');
			
		});
		
		$('#btn2').click(function() {
			
			//console.log(document.cookie); //홍길동,100,90,80 -> 쿠키 읽기
			//console.log(document.cookie); //name=홍길동&kor=100&eng=90&math=80
			
			console.log(getCookie('name'));
			console.log(getCookie('kor'));
			console.log(getCookie('eng'));
			console.log(getCookie('math'));
		});
		
		
	</script>
</body>
</html>




