<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>
	
</style>
</head>
<body>
	
	<h1>로그인</h1>
	<form method="POST" action="ex26_cookie_ok.jsp">
		<table class="vertical content">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="txtId" required class="short"></td>
			</tr>
			<tr>
				<th>암호</th>
				<td><input type="password" name="txtPw" required class="short"></td>
			</tr>
		</table>
		
		
		
		<div>
			<div style="margin-bottom:10px;">
				<label><input type="checkbox" id="cbRemember" name="cbRemember" value="y">아이디 기억하기</label>
			</div>
			<input type="submit" value="로그인">
		</div>
	</form>
	
	<hr>

	<c:if test="${result}">	
		<div class="message">인증 O</div>
	</c:if>

	<c:if test="${not result}">	
		<div class="message">인증 X</div>
	</c:if>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="/jsp/js/cookie.js"></script>
	<script>
	
		
		//아이디 기억하기 > 초기화
		if (getCookie('id') != '') {
			$('input[name=txtId]').val(getCookie('id'));
			$('input[name=txtPw]').focus();
			$('#cbRemember').prop('checked', true);
		} else {
			//처음 방문
			$('input[name=txtId]').focus(); //txtId에
		}
		
		
	</script>
</body>
</html>




