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
	div > div { margin-bottom: 5px; }
	#loader { display: none; }
</style>
</head>
<body>
	<!-- ex04.jsp -->
	<h1>Ajax</h1>
	
	<!--  
	
		Ajax, Asynchronous JavaScript and XML
		
		비동기 통신 vs 동기 통신
		
		
		
	
		1. GET
		- 주고 + 받기
	-->
	<div>
		<div><input type="text" id="txt1"></div>
		<div><input type="button" value="확인" id="btn1"></div>
		<div id="div1"></div>
		<div><img src="/ajax/images/loader.gif" id="loader"></div>
	</div>
	
	<hr>
	
	<!--  
		2. GET
		- 주기
	-->
	<div>
		<div><input type="text" id="txt2"></div>
		<div><input type="button" value="확인" id="btn2"></div>
	</div>
	
	<hr>
	
	<!--  
		3. GET
		- 받기
	-->
	<div>
		<div><input type="button" value="확인" id="btn3"></div>
		<div id="div3"></div>
	</div>
	
	<hr>
	
	<!--  
		4. POST
		- 주고 + 받기
	-->
	<div>
		<div><input type="text" id="txt4"></div>
		<div><input type="button" value="확인" id="btn4"></div>
		<div id="div4"></div>
	</div>
	
	<hr>
	
	
	<!--  
		5. GET
		- 주고 + 받기
	-->
	<div>
		<div><input type="text" id="txt5"></div>
		<div><input type="button" value="확인" id="btn5"></div>
		<div id="div5"></div>
	</div>
	
	<hr>
	
	<div><input type="text"></div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	
		$('#btn1').click(()=>{
			
			const ajax = new XMLHttpRequest();
			
			ajax.onreadystatechange = function(evt) {
				if (ajax.readyState == 4 && ajax.status == 200) {
					
					$('#div1').text(ajax.responseText); //보낸 문자열의 글자수
					$('#loader').hide();
					
				}
			};
			
			//GET 호출 + 데이터 전송 = Query String
			ajax.open('GET', '/ajax/ex04data.do?txt1=' + $('#txt1').val(), true);
			
			ajax.send();
			
			$('#loader').show();
			
		});
		
		$('#btn2').click(() => {
			
			const ajax = new XMLHttpRequest();
			
			//onreadystatechange > 데이터 수신할 때 사용
			
			ajax.open('GET', '/ajax/ex04data.do?txt2=' + $('#txt2').val());
			
			ajax.send();
			
		});
		
		$('#btn3').click(() => {
			
			const ajax = new XMLHttpRequest();
			
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 4 && ajax.status == 200) {
					$('#div3').text(ajax.responseText);
				}
			}
			
			ajax.open('GET', '/ajax/ex04data.do');
			
			ajax.send();
			
		});
		
		$('#btn4').click(()=>{
			
			const ajax = new XMLHttpRequest();
			
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 4 && ajax.status == 200) {
					
					$('#div4').text(ajax.responseText);
					
				}
			};
			
			ajax.open('POST', '/ajax/ex04data.do');
			
			ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			
			//POST 데이터 전송
			ajax.send('txt4=' + $('#txt4').val());
			
		});
		
		$('#btn5').click(() => {
			
			/*
			//$(태그).css()
			$.ajax({
				
				//페이지 요청 정보
				type: 'GET',
				url: '/ajax/ex04data.do',
				
				//데이터 전송
				data: 'txt1=' + $('#txt5').val(),
				
				//결과 수신 이벤트
				success: function(result) {
					
					$('#div5').text(result);
					
				}
			});
			*/
			
			//주기
			/*
			$.ajax({
				type: 'GET',
				url: '/ajax/ex04data.do',
				data: 'txt2=100'
			});
			*/
			
			//받기
			/*
			$.ajax({
				type: 'GET',
				url: '/ajax/ex04data.do',
				success: function(result) {
					
				}
			});
			*/
			
			//POST
			$.ajax({
				type: 'POST',
				url: '/ajax/ex04data.do',
				data: 'txt4=' + $('#txt5').val(),
				success: function(result) {
					$('#div5').text(result);
				}
			});
			
		});
		
		
	
	</script>
</body>
</html>










