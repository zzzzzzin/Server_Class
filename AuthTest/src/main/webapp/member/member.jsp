<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//인증받지 못한 사용자가 직접 액세스 > 처리
	if (session.getAttribute("id") == null) {
		
		//response.sendRedirect("/auth/index.jsp");
		
		out.println("<script>alert('invalid access'); location.href='/auth/index.jsp';");
		out.close();
		
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
	<!-- member.jsp -->
	<h1>회원 전용 페이지</h1>
	
	<div>
		<a href="/auth/index.jsp">시작 페이지</a>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
	</script>
</body>
</html>










