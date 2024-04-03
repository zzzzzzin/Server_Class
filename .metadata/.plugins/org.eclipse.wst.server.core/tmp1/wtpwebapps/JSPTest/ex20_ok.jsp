<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String path = application.getRealPath("/files");
	int size = 1024 * 1024 * 100;
	
	String subject = "";
	
	ArrayList<String> filename = new ArrayList<String>();
	ArrayList<String> orgfilename = new ArrayList<String>();
	
	try {
		
		MultipartRequest multi = new MultipartRequest(
									request,
									path,
									size,
									"UTF-8",
									new DefaultFileRenamePolicy()
								);
		
		subject = multi.getParameter("subject");
		
		//<input type="file" name="attach1">
		//<input type="file" name="attach2">
		//<input type="file" name="attach3">
		
		Enumeration e = multi.getFileNames();
		
		while (e.hasMoreElements()) {
			
			String file = (String)e.nextElement();
			//System.out.println(file);
			
			//System.out.println(multi.getFilesystemName(file));
			
			filename.add(multi.getFilesystemName(file));
			orgfilename.add(multi.getOriginalFileName(file));
			
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
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
	<!-- ex20_ok.jsp -->
	<h1>결과</h1>
	
	<div>
		제목: <%= subject %>
	</div>
	
	<div>
		<div>첨부파일: <%= filename.size() %>개</div>
		<% for (int i=0; i<filename.size(); i++) { %>
		<div>
			<a href="download.jsp?filename=<%= filename.get(i) %>&orgfilename=<%= orgfilename.get(i) %>"><%= orgfilename.get(i) %></a>
		</div>
		<% } %>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
	</script>
</body>
</html>










