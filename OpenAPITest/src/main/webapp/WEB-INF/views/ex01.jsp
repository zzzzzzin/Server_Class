<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Naver Book</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>
	
	table td:nth-child(1) { width: 120px; text-align: center; }
	table td:nth-child(2) div { margin-bottom: 5px; }
	table img { width: 110px; }
</style>
</head>
<body>
	<!-- ex01.jsp -->
	<h1 class="sub">Naver Book <small>Search</small></h1>
	
	<form method="GET" action="/api/ex01.do">
	<div class="message">
		<div class="group">
			<label>검색어</label>
			<input type="text" name="word" class="long" required value="${word}">
			<input type="submit" value="검색하기">
			<input type="button" value="초기화" onclick="location.href='/api/ex01.do';">
		</div>
	</div>
	</form>
	
	<table>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td><a href="${dto.link}" target="_blank"><img src="${dto.image}"></a></td>
			<td>
				<div>${dto.title}</div>
				<div>${dto.author}</div>
				<div>${dto.discount}</div>
				<div>${dto.publisher}</div>
				<%-- <div>${dto.description}</div> --%>
			</td>			
		</tr>
		</c:forEach>
	</table>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
	</script>
</body>
</html>










