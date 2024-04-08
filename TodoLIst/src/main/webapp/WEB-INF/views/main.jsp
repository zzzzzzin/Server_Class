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
	
	#tbl1 td:nth-child(1) { width: 30px; text-align: center; vertical-align: middle; font-size: 0; }
	#tbl1 td:nth-child(2) { cursor: pointer; }
	#tbl1 td:nth-child(2) > span { font-size: 12px; color: #999; }
	
	#tbl2 td:nth-child(1) { width: 360px; }
	#tbl2 td:nth-child(2) {}
	
	.checked {
		text-decoration: line-through;
	}
	
</style>
</head>
<body class="narrow">
	<!-- main.jsp -->
	
	<h1 class="main">Todo List</h1>
		
	<table id="tbl1">
		
		<c:forEach items="${nlist}" var="dto">
		<tr>
			<td><input type="checkbox" data-seq="${dto.seq}"></td>
			<td>${dto.todo} <span>${dto.regdate}</span></td>
		</tr>
		</c:forEach>
		<c:forEach items="${ylist}" var="dto">
		<tr>
			<td><input type="checkbox" checked data-seq="${dto.seq}"></td>
			<td class="checked">${dto.todo} <span>${dto.regdate}</span></td>
		</tr>
		</c:forEach>
		
		<%-- <c:forEach items="${list}" var="dto">
		
		<% -- 
		<c:if test="${dto.state == 'y'}">
			<c:set var="checked" value="class='checked'" />
		</c:if>
		<c:if test="${dto.state == 'n'}">
			<c:set var="checked" value="" />
		</c:if> 
		-- %>
		
		<tr>
			<td><input type="checkbox"></td>
			<% -- <td <c:if test="${dto.state == 'y'}">style="text-decoration: line-through;"</c:if>>${dto.todo} <span>${dto.regdate}</span></td> -- %>
			<td ${dto.checked}>${dto.todo} <span>${dto.regdate}</span></td>
		</tr>
		</c:forEach> --%>
	</table>
	<hr>
	<form method="POST" action="/todo/addok.do">
	<table id="tbl2">
		<tr>
			<td><input type="text" name="todo" required class="long"></td>
			<td><input type="submit" value="등록하기"></td>
		</tr>
	</table>
	</form>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	
		$('#tbl1 tr').click(() => {
			//alert(event.target.nodeName);
			//alert(event.currentTarget.nodeName);
			//alert(event.currentTarget.firstElementChild.firstElementChild.dataset['seq']);
			location.href = '/todo/checkok.do?seq='
				+ event.currentTarget.firstElementChild.firstElementChild.dataset['seq'];
		});
	
		$('#tbl1 input[type=checkbox]').change(() => {
			//alert(event.target.checked);
			//alert(event.target.dataset['seq']);
			
			location.href = '/todo/checkok.do?seq='
							+ event.target.dataset['seq'];
			
		});
	
	</script>
</body>
</html>










