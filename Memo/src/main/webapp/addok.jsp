<%@page import="com.test.memo.MemoDTO"%>
<%@page import="com.test.memo.MemoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	//1. add.jsp에서 작성한 데이터 가져오기
	//2. DB 작업 > DTO 포장 > DAO 위임 > insert
	//3. 실행 결과 > 피드백
	
	//POST로 한글 => 인코딩하기
	request.setCharacterEncoding("UTF-8");
	
	//데이터 가져오기
	String name = request.getParameter("name");
	String pw = request.getParameter("pw");
	String memo = request.getParameter("memo");
	
	//DB 작업 > insert
	MemoDAO dao = new MemoDAO();

	MemoDTO dto = new MemoDTO();
	dto.setName(name);
	dto.setPw(pw);
	dto.setMemo(memo);
	
	int result = dao.add(dto);
	
	System.out.println(result);
	
	if (result > 0 ) {
		response.sendRedirect("/memo/list.jsp"); //자바코드라도 sendRedirect만 '/루트 폴더' 로 작성
	} else {
		response.sendRedirect("<script>alert('failed'); history.back();</script>");
		out.close();
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
	<!-- addok.jsp -->
	<%@ include file="/inc/header.jsp" %>
	
	<h1>Memo <small>쓰기</small></h1>
	
	<% if (result > 0) { %>
	<div class="message">
		메모 작성을 완료했습니다.
	</div>
	<div>
		<button type="button" class="back" onclick="location.href='/memo/list.jsp';">돌아가기</button>
	</div>
	<% } else { %>
	<div class="message">
		메모 작성을 실패했습니다.
	</div>
	<div>
		<button type="button" class="back" 
				onclick="history.back();">돌아가기</button>
	</div>
	<% } %>
	
	<script>
		
		<% if (result > 0) { %>
		alert('메모 작성을 완료했습니다.');
		location.href = '/memo/list.jsp';
		<% } else { %>
		alert('메모 작성을 실패했습니다.');
		history.back();
		<% } %>
	</script>
</body>
</html>




