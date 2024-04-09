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
	html, body {
		overflow: hidden;
	}
</style>
</head>
<body>
	<!-- ex05check.jsp -->
	<h2>아이디 중복검사</h2>
	
	<form method="GET" action="/ajax/ex05check.do">
	<table class="vertical">
		<tr>
			<th style="width: 120px;">아이디</th>
			<td>
				<input type="text" name="id" id="id" class="short" required value="${id}">
				<input type="submit" value="검사">
			</td>
		</tr>
	</table>
	</form>
	<div>
		<c:if test="${not empty result}">
		<div style="color: tomato;">${result == 1 ? "이미 사용중인 아이디입니다." : "사용 가능한 아이디입니다."}</div>
		</c:if>
	</div>
	<hr>
	<div>
		<input type="button" value="사용하기" id="btnUse" disabled>
		<input type="button" value="창닫기" id="btnClose">
	</div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	
		$('#btnClose').click(()=>{
			window.close();
		});
		
		$('#btnUse').click(()=>{
			window.opener.document.getElementById('id').value
				= $('#id').val();
			window.close();
		});
	
		<c:if test="${result == 0}">
		$('#btnUse').prop('disabled', false);
		</c:if>
		<c:if test="${result == 1}">
		$('#btnUse').prop('disabled', true);
		</c:if>
		
		$('#id').change(function() {
			//alert('아이디를 수정했습니다.\n다시 중복검사를 실행하세요.');
			$('#btnUse').prop('disabled', true);
		});
	
	</script>
</body>
</html>










