<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Memo</title>

<%@ include file="/inc/asset.jsp" %>



<style>
	
</style>
</head>
<body>
	
	<%@ include file="/inc/header.jsp" %>
	
	<h1>Memo <small>쓰기</small></h1>
	
	<form method="POST"  action="/memo/addok.jsp">
		<table class="vertical">
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" required></td>
			</tr>
			<tr>
				<th>암호</th>
				<td><input type="password" name="pw" required></td>
			</tr>
			<tr>
				<th>메모</th>
				<td><textarea name="memo" class="full" required></textarea></td>
			</tr>
		</table>
		<div>
			<button type="submit" class="add">쓰기</button>
			<button type="submit" class="back" onclick="location.href='/memo/list.jsp';">돌아가기</button>
		</div>
	
	
	</form>
	
	
	<script>
		
	</script>
</body>
</html>




