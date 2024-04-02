<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h1>세션값 삭제하기</h1>
	<%
		//집합 조작 메소드명 패턴
		//- addXXX(): 추가하기(append, 마지막에 추가)
		//- insertXXX(): 추가하기(insert, 원하는 위치에 추가)
		//- appendXXX(): 추가하기(맨끝에)
		//- prependXXX(): 추가하기(맨앞에)
		//- setXXX(): 추가하기
		//- getXXX(): 가져오기
		//- remove(): 삭제하기
		//- isXXX(): 확인(반환값 boolean) > 뭐가 맞는지 확인 여부에 사용
		//- containXXX(): 확인 > 대상이 존재하는지에 사용
		
		session.removeAttribute("data");
		
	%>
	<div> <a href="ex18_session.jsp">돌아가기</a></div>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
	</script>
</body>
</html>




