<%@page import="com.test.auth.UserDTO"%>
<%@page import="com.test.auth.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//1. 데이터 가져오기(id, pw)
	//2. DB 작업 > select
	//3. 결과 > 인증 티켓 발급
	//4. 피드백
	
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	UserDAO dao = new UserDAO();
	
	UserDTO dto = new UserDTO();
	dto.setId(id);
	dto.setPw(pw);
	
	UserDTO result = dao.check(dto);
	
	if (result != null) {
		
		//인증 티켓 발급
		session.setAttribute("id", id);
		
		//추가 정보
		session.setAttribute("name", result.getName());
		session.setAttribute("lv", result.getLv());
		
		response.sendRedirect("/auth/index.jsp");
		
	} else {
		
		response.sendRedirect("/auth/auth/login.jsp");
		
	}	

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>
	
</style>
</head>
<body>
	<!-- loginok.jsp -->
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
	</script>
</body>
</html>










