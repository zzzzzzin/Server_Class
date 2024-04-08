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
	<!-- addok.jsp -->
	
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	
		<c:if test="${result == 1}">
		location.href = '/todo/main.do';
		</c:if>
		
		<c:if test="${result == 0}">
		alert('failed');
		history.back();
		</c:if>
	
	</script>
</body>
</html>










