<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp" %>
<link rel="stylesheet" href="/toy/asset/css/tagify.css">
<style>
	
</style>
</head>
<body>
	<!-- add.jsp > edit.jsp -->
	<%@include file="/WEB-INF/views/inc/header.jsp" %>
	
	<div id="main">
		
		<h1 class="sub">게시판 <small>수정하기</small></h1>
		
		<form id="editForm" method="POST" action="/toy/board/edit.do">
		<table class="vertical">
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" id="subject" required class="full" value="${dto.subject}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" id="content" required class="full">${dto.content}</textarea></td>
			</tr>
			<tr>
				<th>태그</th>
				<td><input type="text" name="tag" id="tag" class="full"></td>
			</tr>
		</table>
		<div class="group">
			<button type="button" class="back" onclick="location.href='/toy/board/view.do?seq=${seq}';">돌아가기</button>
			<button type="submit" class="edit primary">수정하기</button>
		</div>
		
		<input type="hidden" name="seq" value="${seq}">
		</form>
		
	</div>
	
	<script src="/toy/asset/js/tagify.min.js"></script>
	<script>
		
		let taglist = '';
		<c:forEach items="${dto.tag}" var="tag">
		taglist += '${tag},';
		</c:forEach>
		
		$('#tag').val(taglist);
	
		const tagify = new Tagify(document.getElementById('tag'));
		tagify.on('remove', (e)=>{
			
			//alert(e.detail.data.value);
			$('#editForm').append(`<input type="hidden" name="removeTag" value="\${e.detail.data.value}">`);
			
		}).on('add', (e)=>{
			//alert(e.detail.data.value);
			$(`#editForm input[value=\${e.detail.data.value}]`).remove();
		});
		
	</script>
	
</body>
</html>










