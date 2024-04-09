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
	#ifame1 {
		width: 100%;
		height: 600px;
		
	}
</style>
</head>
<body>
	
	<!-- 문서내의 일정 영역을 잡고 다른 페이지 보여줌 => 독립된 브라우저 창 -->
	<iframe id="ifame1" src="/ajax/ex01.do" frameborder="0" scrolling="no"></iframe> 
	
	<hr>
	<h2>다른 내용</h2>
	
	<div>
		<input type="text">
		<br><br>
		<input type="checkbox">
	</div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
	</script>
</body>
</html>




