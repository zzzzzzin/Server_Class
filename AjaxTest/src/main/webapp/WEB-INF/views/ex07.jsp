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
	<!-- ex07.jsp -->
	<h1>Ajax 응답 데이터(형식에 따른 분류)</h1>
	
	<h2>Text <small>ajax.responseText</small></h2>
	<div>
		<input type="button" value="확인" id="btn1">
		<div class="message" id="div1"></div>
	</div>
	
	<h2>XML <small>ajax.responseXML</small></h2>
	<div>
		<input type="button" value="확인" id="btn2">
		<div class="message" id="div2"></div>
	</div>
	
	<h2>JSON(***)</h2>
	<div>
		<input type="button" value="확인" id="btn3">
		<div class="message" id="div3"></div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	

		/*
		
			$.ajax() 함수 구문
			
			$.ajax({
				type: 'GET', 					//메소드 방식
				url: '/ex01.do' 				//서버측 URL
				async: true,					//동기(false), 비동기(true)
				data: 'key=value&key=value',	//전송할 데이터
				dataType: 'text',			   //수신할데이터타입(text, xml, json)
				success: function(result) {
					console.log(result);		//수신된 데이터
				},
				error: function(a,b,c) {
					console.log(a,b,c);			//예외 처리
				}
			});			
		
		
		*/
	
	
	
		//텍스트 반환 + 단일값
		$('#btn1').click(()=>{
			
			$.ajax({
				type: 'GET',
				url: '/ajax/ex07check.do',
				
				//전송하는 데이터(값)
				//data: 'type=1',
				data: 'type=2',
				
				//돌려받는 데이터의 형식(text, xml, json) > result 타입
				dataType: 'text',
				
				success: function(result) {
					
					//$('#div1').text(result);
					
					const lines = result.trim().split('\r\n');
					
					lines.forEach((line) => {
						const column = line.split(',');
						$('#div1').append(
							`<ul>
								<li>아이디: \${column[0]}</li>
								<li>암호: \${column[1]}</li>
								<li>이름: \${column[2]}</li>
								<li>등급: \${column[3]}</li>
							 </ul>
							`
						);
					});
					
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}				
			});
			
		});
		
		
		$('#btn2').click(()=>{
			
			$.ajax({
				type: 'GET',
				url: '/ajax/ex07check.do',
				data: 'type=4',
				
				dataType: 'xml',
				
				success: function(result) {
					//alert(result);
					
					//alert($(result).find('result').text());
					//alert($(result).find('#q1').text());
					//$('#div2').text($(result).find('#q1').text());
					
						$(result).find('user').each((index, item)=>{
						
						let temp = '';
						temp += '<ul>';
						temp += `<li>이름: \${$(item).find('name').text()}</li>`;
						temp += `<li>아이디: \${$(item).find('id').text()}</li>`;
						temp += `<li>암호: \${$(item).find('pw').text()}</li>`;
						temp += `<li>등급: \${$(item).find('lv').text()}</li>`;
						temp += '</ul>';
						
						$('#div2').append(temp);
						
					});
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
				
			});
			
		});
		
		$('#btn3').click(() => {
			$.ajax({
				type: 'GET',
				url: '/ajax/ex07check.do',
				data: 'type=5',
				
				dataType: 'json',
				
				success: function(result) {
					
					/*
						result = {
							"question": "가장 자신있는 프로그래밍 언어는?"
						};
					*/
					
					//alert(typeof result);
					//alert(result.question);
					
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
				
				
				
		});	
	
	</script>
</body>
</html>










