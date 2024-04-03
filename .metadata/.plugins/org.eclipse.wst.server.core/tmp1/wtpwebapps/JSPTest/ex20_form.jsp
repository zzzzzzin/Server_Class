<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>
	#fileList > div {
		margin-top: 5px;
	}
	
	#fileList > div > span {
		font-size: 1.5rem;
		margin-left: 5px;
		cursor: pointer;
	}
</style>
</head>
<body>
	<!-- ex20_form.jsp -->
	<h1>다중 파일 업로드</h1>
	
	<form method="POST" action="ex20_ok.jsp" enctype="multipart/form-data">
	<table class="vertical">
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject"></td>
		</tr>
		<tr>
			<th>파일</th>
			<td>
				<div><input type="file" name="attach1"></div>
				<div id="fileList"></div>
				<hr>
				<input type="button" value="첨부 파일 추가하기" id="btnFile">
				
			</td>
		</tr>
	</table>
	<div>
		<input type="submit" value="보내기">
	</div>
	</form>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
		let no = 2;
		
		$('#btnFile').click(function() {
			
			$('#fileList').append('<div><input type="file" name="attach' + no + '"><span onclick="del();">&times;</span></div>');
			no++;
			
		});
		
		function del() {
			$(event.target).parent().remove();
		}
	
	</script>
</body>
</html>










