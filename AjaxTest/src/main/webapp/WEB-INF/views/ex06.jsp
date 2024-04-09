<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
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
	<h1>아이디 중복 검사</h1>
	
	<table class="vertical">
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" id="name"></td>
		</tr>
		<tr>
			<th>아이디</th>
			<td>
				<input type="text" name="id" id="id">
				<input type="button" value="중복검사" id="btnCheck">
				<span id="result" style="color: tomato;"></span>
			</td>
		</tr>
	</table>
	<hr>
	
	<div>
		<input type="submit" value="가입하기">
	</div>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
		$('#btnCheck').click(() => {
			
			$.ajax({
				
				type: 'POST',
				url: '/ajax/ex06check.do',
				data: 'id=' + $('#id').val(),
				success: function (result) {
					
					if (result == 0) {
						$('#result').text('사용 가능한 아이디입니다.');
					} else {
						$('#result').text('이미 사용중인 아이디입니다.');
					}
					
				},
				error: function (a, b, c) {
					console.log(a, b, c);
				}
				
			});
			
		});
	
	</script>
</body>
</html>




