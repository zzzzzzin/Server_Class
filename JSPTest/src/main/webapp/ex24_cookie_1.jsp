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

	<h1>두번째 페이지</h1>
	
	<div id="result"></div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="/jsp/js/cookie.js"></script>
	<script>
		
		$('#result').text(getCookie('name'));
	
	</script>
</body>
</html>




