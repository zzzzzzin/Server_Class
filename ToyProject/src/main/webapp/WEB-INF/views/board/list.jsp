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
		
		<c:if test="${map.search == 'y'}">
		<div id="labelSearchResult">
			'${map.word}'(으)로 검색한 결과 ${totalCount}건이 있습니다.
		</div>
		</c:if>
		
		<%-- 
		<div id="pagebar">
			<input type="number" class="short" id="page" value="${nowPage}" min="1" max="${totalPage}">
			<input type="button" value="이동하기" onclick="location.href='/toy/board/list.do?page=' + $('#page').val();">
		</div> 
		--%>
		
		<%-- <div id="pagebar">
			<select onchange="location.href='/toy/board/list.do?page=' +  $(this).val();">
				<c:forEach begin="1" end="${totalPage}" var="i">
				<option value="${i}" <c:if test="${i == nowPage}">selected</c:if>>${i}페이지</option>
				</c:forEach>
			</select>
		</div> --%> 
		
		<table id="list">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>이름</th>
				<th>날짜</th>
				<th>읽음</th>
			</tr>
			<c:if test="${list.size() == 0}">
			<tr>
				<td colspan="5">게시물이 없습니다.</td>
			</tr>
			</c:if>
			<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.seq}</td>
				<td>
					
					<!--  
						view.do?seq=10
						view.do?seq=10&column=subject&word=검색어&search=y
					-->
					<a href="/toy/board/view.do?seq=${dto.seq}&column=${map.column}&word=${map.word}&search=${map.search}&page=${nowPage}">${dto.subject}</a>
					
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
		
		<!-- 검색 인터페이스 -->
		<form id="searchForm" method="GET" action="/toy/board/list.do">
			<select name="column">
				<option value="subject">제목</option>
				<option value="content">내용</option>
				<option value="name">이름</option>
			</select>
			<input type="text" name="word" class="long" required>
			<input type="submit" value="검색하기">
		</form>
		
		<!-- 페이지바 -->
		<div id="pagebar">${pagebar}</div>
		
		<div>
			<!-- 
			<button type="button" class="back" onclick="location.href='';"></button> 
			-->
			<c:if test="${not empty id}">
			<button type="button" class="add primary" onclick="location.href='/toy/board/add.do';">쓰기</button>
			</c:if>
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
	
		<c:if test="${map.search == 'y'}">
		//검색중 상태 유지
		$('input[name=word]').val('${map.word}');
		$('select[name=column]').val('${map.column}');
		</c:if>
	
	</script>
</body>
</html>










