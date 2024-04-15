<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
	
</style>
</head>
<body>
	<!-- login.jsp -->
	<%@include file="/WEB-INF/views/inc/header.jsp" %>
	
	<div id="main">
		
		<h1 class="sub">회원 <small>로그인</small></h1>
		<form method="POST" action="/toy/user/login.do" >
			<table class="vertical">
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id" id="id" required class="short"></td>
				</tr>
				<tr>
					<th>암호</th>
					<td><input type="password" name="pw" id="pw" required class="short"></td>
				</tr>
			</table>
			<div>
				<button type="button" class="back" onclick="location.href='/toy/index.do';">돌아가기</button>
				<button type="submit" class="login primary">로그인</button>
			</div>
		</form>
		
	</div>
	
	<hr>
	
	<div style="display: flex; justify-content: space-between;">
		<form method="POST" action="/toy/user/login.do">
			<input type="hidden" name="id" value="hong">
			<input type="hidden" name="pw" value="1111">
			<input type="submit" value="홍길동(hong)">
		</form>
		<form method="POST" action="/toy/user/login.do">
			<input type="hidden" name="id" value="dog">
			<input type="hidden" name="pw" value="1111">
			<input type="submit" value="강아지(dog)">
		</form>
		<form method="POST" action="/toy/user/login.do">
			<input type="hidden" name="id" value="cat">
			<input type="hidden" name="pw" value="1111">
			<input type="submit" value="고양이(cat)">
		</form>
		<form method="POST" action="/toy/user/login.do">
			<input type="hidden" name="id" value="tiger">
			<input type="hidden" name="pw" value="1111">
			<input type="submit" value="호랑이(tiger)">
		</form>
	</div>
	
	<script>
		
	</script>
</body>
</html>










