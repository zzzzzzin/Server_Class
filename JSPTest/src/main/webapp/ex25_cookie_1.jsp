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
	<h1>사이트 <small>두번째 페이지</small></h1>
	<div>
		<a href="ex25_cookie.jsp">첫번째 페이지</a>
		<a href="ex25_cookie_1.jsp">두번째 페이지</a>
		<a href="ex25_cookie_2.jsp">세번째 페이지</a>
	</div>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="/jsp/js/cookie.js"></script>
	<script>
		if(getCookie('theme') != '') {
			$('body').css('background-color', getCookie('theme'));
			
		}
		
			
	</script>
</body>
</html>




