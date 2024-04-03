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
	<h1>파일 업로드</h1>
	
	<!-- 
		파일 업로드 설정
		1. <form method="POST">
		2. <form enctype="">
			a. application/x-www-form-urlencoded
				- 전송값들을 문자열로 전송하겠다.
			b. multipart/form-data
				- 문자열 + 비문자열 동시 전송
		3. <input type="file">
		
	-->
	
	<form method="POST" action="ex19_ok.jsp"
		enctype="multipart/form-data">
		
		<table class="vetrical">
			<tr>
				<th>문자</th>
				<td><input type="text" name="txt"></td>
			</tr>
			<tr>
				<th>숫자</th>
				<td><input type="number" name="num"></td>
			</tr>
			<tr>
				<th>파일</th>
				<td><input type="file" name="attach"></td>
			</tr>
		</table>
		<div>
			<input type="submit" value="보내기">
		</div>
	</form>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
	</script>
</body>
</html>




