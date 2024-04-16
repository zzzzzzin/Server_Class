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
	<!-- view.jsp -->
	<%@include file="/WEB-INF/views/inc/header.jsp" %>
	
	<div id="main">
		
		<h1 class="sub">게시판 <small>상세보기</small></h1>
		
		<table class="vertical" id="view">
			<tr>
				<th>번호</th>
				<td>${dto.seq}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${dto.name}(${dto.id})</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.subject}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${dto.content}</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td>${dto.regdate}</td>
			</tr>
			<tr>
				<th>읽음</th>
				<td>${dto.readcount}</td>
			</tr>
		</table>
		
		
		<!-- 댓글 보기 -->
		<table id="comment">
			<tr>
				<td class="commentContent">
					<div>댓글내용입니다.</div>
					<div>2024-04-16 17:07:03</div>
				</td>
				<td class="commentInfo">
					<div>홍길동(hong)</div>
				</td>
			</tr>
			<tr>
				<td class="commentContent">
					<div>댓글내용입니다.</div>
					<div>2024-04-16 17:07:03</div>
				</td>
				<td class="commentInfo">
					<div>홍길동(hong)</div>
				</td>
			</tr>
			<tr>
				<td class="commentContent">
					<div>댓글내용입니다.</div>
					<div>2024-04-16 17:07:03</div>
				</td>
				<td class="commentInfo">
					<div>홍길동(hong)</div>
				</td>
			</tr>
		</table>
		
		<!-- 댓글 쓰기 -->
		<form id="addCommentForm">
		<table id="addComment">
			<tr>
				<td><input type="text" name="content" class="full" required></td>
				<td><button type="button" class="comment" id="btnAddComment">댓글 쓰기</button></td>
			</tr>
		</table>
		</form>
		
		<div>
			<button type="button" class="back" onclick="location.href='/toy/board/list.do?column=${column}&word=${word}&page=${page}';">돌아가기</button>
			<!-- <button type="button" class="back" onclick="history.back();">돌아가기</button> -->
			
			<c:if test="${not empty id && (dto.id == id || lv == '2')}">
			<button type="button" class="edit" onclick="location.href='/toy/board/edit.do?seq=${dto.seq}';">수정하기</button>
			<button type="button" class="del" onclick="location.href='/toy/board/del.do?seq=${dto.seq}';">삭제하기</button>
			</c:if>
			
			
			
			<!-- <button type="button" class="" onclick="location.href='';"></button> -->
		</div>
		
	</div>
	
	<script>
	
		$('#btnAddComment').click(() => {
						
			$.ajax({
				type: 'POST',
				url: '/toy/board/addcomment.do',
				data: {
					content: $('input[name=content]').val(),
					bseq: ${dto.seq}
				},
				dataType: 'json',
				success: function(result) {
					
					if (result.result == '1') {
						
						alert('성공');
						$('input[name=content]').val('');
						
					} else {
						alert('댓글 등록을 실패했습니다.');
					}
					
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			
		});
		
		//form.onsubmit
		$('#addCommentForm').submit(() => {
			
			$('#btnAddComment').click();
			
			event.preventDefault();
		});
	
	</script>
</body>
</html>










