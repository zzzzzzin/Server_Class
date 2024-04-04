<%@page import="com.test.memo.MemoDTO"%>
<%@page import="com.test.memo.MemoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

	//1. 데이터 가져오기
	//2. DB 작업 > DTO 포장 > DAO 위임 > update
	//3. 실행 결과 > 피드백
	
	//POST + 한글
	request.setCharacterEncoding("UTF-8");
	
	//데이터 가져오기
	String pw = request.getParameter("pw");
	String memo = request.getParameter("memo");
	String seq = request.getParameter("seq");
	
	//DB 작업 > insert
	MemoDAO dao = new MemoDAO();
	
	MemoDTO dto = new MemoDTO();
	dto.setPw(pw);
	dto.setMemo(memo);
	dto.setSeq(seq);
	
	int result = 0;
	
	if (dao.check(dto)) {
		result = dao.edit(dto);
	}
		
	if (result > 0) {
		response.sendRedirect("/memo/list.jsp");
	} else {
		out.println("<script>alert('failed'); history.back();</script>");
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
	<!-- addok.jsp > editok.jsp -->
	<%@ include file="/inc/header.jsp" %>
	
	<h1>Memo <small>쓰기</small></h1>
	
	
</body>
</html>










