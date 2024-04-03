<%@page import="java.util.Arrays"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = application.getRealPath("/files");
	
	File dir = new File(path);
	File[] list = dir.listFiles();
	
	Arrays.sort(list, (o1, o2) -> Long.compare(o2.lastModified(), o1.lastModified()));
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>
	#img1 {
		
	}
</style>
</head>
<body>
	<h1>자료실(탐색기) webapp > files</h1>
	
	<table>
		<tr>
			<th>파일명</th>
			<th>크기</th>
			<th>수정된 날짜</th>
		</tr>
			<%for (File file : list) { %>
			<tr id="list">
				<td>
					<img id="img1" src="files/<%= file.getName() %>" style ="max-width: 100px; max-height: 100px;">
					<%= file.getName() %>
					<span title="delete" onclick="del('<%= file.getName() %>');">&times;</span>	
				</td>
				<td><%= file.length() %>B</td>
				<td><%= new java.util.Date(file.lastModified()) %></td>
			</tr>
			<% } %>
		
	</table>
	
	<hr>
	
	<form method="POST" action="ex22_ok.jsp" enctype="multipart/form-data">
	<div>
		파일: <input type="file" name="attach" required id="uploadFile">
	</div>

	<div>
		<input type="submit" value="업로드 ">
	</div>
	</form>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	
		function del(filename) {
			if (confirm('삭제하시겠습니까?')) {
				location.href = 'ex22_del.jsp?filename=' + filename;
			}
		}
		
		function show(filename) {
			alert('삭제하시겠습니까?');
		}
		
		document.querySelectorAll('#list > td:first-child').forEach((item) => {
			item.addEventListener('click', function() {
				$('#img1').attr('src'
				, '/jsp/files/' + event.target.dataset['filename']);
			});
		});
		
		/* $('#uploadFile').change(function(event) {
            var reader = new FileReader();
            reader.onload = function() {
                $('#img1').attr('src', reader.result);
            }
            reader.readAsDataURL(event.target.files[0]);
        }); */
		
		
		
	</script>
</body>
</html>




