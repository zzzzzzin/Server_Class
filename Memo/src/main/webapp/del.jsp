<%@page import="com.test.memo.MemoDTO"%>
<%@page import="com.test.memo.MemoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

	//1. 데이터 가져오기(seq)
	
	String seq = request.getParameter("seq");
	
%>    
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
	<!-- edit.jsp > del.jsp -->
	<%@ include file="/inc/header.jsp" %>
	
	<h1>Memo <small>삭제하기</small></h1>
	
	<form method="POST" action="/memo/delok.jsp">
	<table class="vertical">
		<tr>
			<th>암호</th>
			<td><input type="password" name="pw" required></td>
		</tr>
	</table>
	<hr>
	<div>
		<button type="submit" class="del">삭제하기</button>
		<button type="button" class="back" onclick="location.href='/memo/list.jsp';">돌아가기</button>
	</div>
	<input type="hidden" name="seq" value="<%= seq %>">
	</form>
	
	<script>
		
	</script>
</body>
</html>










