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
	<!-- list.jsp -->
	<%@include file="/WEB-INF/views/inc/header.jsp" %>
	
	<div id="main">
		
		<h1 class="sub">게시판 <small>목록보기</small></h1>
		
		<table id="list">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>이름</th>
				<th>날짜</th>
				<th>읽음</th>
			</tr>
			<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.seq}</td>
				<td>
					<a href="/toy/board/view.do?seq=${dto.seq}">${dto.subject}</a>
					
					<!-- 최신글 표시 -->
					<c:if test="${dto.isnew < 1 / 24}">
					<span class="isnew">new</span>
					</c:if>	
				</td>
				<td>${dto.name}</td>
				<td>${dto.regdate}</td>
				<td>${dto.readcount}</td>
			</tr>
			</c:forEach>
		</table>
		
		
		<div>
			<!-- 
			<button type="button" class="back" onclick="location.href='';"></button> 
			-->
			<button type="button" class="add primary" onclick="location.href='/toy/board/add.do';">쓰기</button>
		</div>
		
		
		<!-- <style>
			#test {
				border: 1px solid black;
				width: 300px;
				height: 24px;
				
				overflow: hidden;
				white-space: nowrap;
				word-break: break-all;
				text-overflow: ellipsis;
			}
		</style>
		<div id="test">
			안녕하세요. 안녕하세요. 안녕하세요. 안녕하세요. 안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요. new
		</div> -->
		
	</div>
	
	<script>
		
	</script>
</body>
</html>










