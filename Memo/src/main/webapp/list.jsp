<%@page import="com.test.memo.MemoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.test.memo.MemoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

	//1. DB 작업 > select
	//2. 결과셋 > 화면 출력
	
	MemoDAO dao = new MemoDAO();
	
	ArrayList<MemoDTO> list = dao.list();
	
	for (MemoDTO dto : list) {
		dto.setMemo(dto.getMemo().replace("\r\n", "<br>"));
	}
	
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
	<!-- list.jsp -->
	<%@ include file="/inc/header.jsp" %>
	
	<h1>Memo <small>목록보기</small></h1>
	
	<div id="list">
		<% for (MemoDTO dto : list) { %>
		<div class="item">
			<!-- html에서는 출력문만 작성하기 (자바영역에서 작성하기) -->
			<div><%= dto.getMemo() %></div> 
			<div>
				<span><%= dto.getName() %></span>
				/
				<span><%= dto.getRegdate() %></span>
			</div>
			<div>
				<button type="button" class="edit" onclick="location.href='/memo/edit.jsp?seq=<%= dto.getSeq() %>';">수정하기</button>
				<button type="button" class="del" onclick="location.href='/memo/del.jsp?seq=<%= dto.getSeq() %>';">삭제하기</button>
			</div>
		</div><!-- .item -->
		<% } %>
		
	</div>
	<hr>
	<div>
		<button type="button" class="add" onclick="location.href='/memo/add.jsp';">쓰기</button>
	</div>
	
	<script>
		
	</script>
</body>
</html>










