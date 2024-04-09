<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
	<!-- ex03.jsp -->
	<h1>Ajax</h1>
	
	<!-- 버튼 클릭 > 서버의 데이터를 가져와서 화면에 출력하기 -->
	<form action="/ajax/ex03data.do">
		<div><a href="/ajax/ex03data.do">확인하기</a></div>
		<div><input type="submit" value="확인하기"></div>
		<div><input type="button" value="확인하기" id="btn1"></div>
		<hr>
		<pre class="message">${count}</pre>
	</form>
	<hr>
	<div>
		다른 업무: <input type="text">
	</div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	
		$('#btn1').click(() => {
			
			/*
				Ajax
				1. 순수 자바스크립트
				2. 각종 라이브러리(jQuery)
			*/
			
			//Ajax > 서버에게 요청 + 응답 > Ajax 객체
			const ajax = new XMLHttpRequest();
			
			ajax.onreadystatechange = function() {
				//$('.message').append('<div>수신 완료</div>');
				//$('.message').append(`<div>\${ajax.readyState}</div>`);
				$('.message').append(`<div>\${ajax.status}</div>`);
				
				if (ajax.readyState == 4 && ajax.status == 200) {
					//서버가 돌려준 데이터
					$('.message').append(ajax.responseText);
					//alert("@" + ajax.responseText + "@");
					
				}
			};
			
			//<form method="GET" action="/ajax/ex03.txt">
			//ajax.open('GET', '/ajax/ex03.txt'); //연결(X), 설정(O)
			ajax.open('GET', '/ajax/ex03data.do');
			
			ajax.send(); //실제 요청 + 서버와 연결 > 전송 버튼 or 링크 클릭					
			
		});
	
	</script>
</body>
</html>










