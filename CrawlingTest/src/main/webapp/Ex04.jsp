<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@page import="org.jsoup.Jsoup"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 

	Document doc = Jsoup.connect("https://www.moviechart.co.kr/rank/boxoffice").get();

	Elements list = doc.select(".listTable tbody tr");
	
	list.sort((a, b) -> {
		
		return Integer.parseInt(b.select(".cumulative").text().replace(",", "").replace("명", "").trim()) - Integer.parseInt(a.select(".cumulative").text().replace(",", "").replace("명", "").trim());
	});

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>
	table th:nth-child(1) { width: 190px; }
	table th:nth-child(2) { width: 50px; }
	table th:nth-child(3) { width: auto; }
	table th:nth-child(4) { width: 120px; } 
	table img { width: 150px; }
	table td { text-align: center; }
	table td:nth-child(3) { text-align: left; }
</style>
</head>
<body>
	<!-- ex04.jsp -->
	<h1>영화 순위</h1>
	
	<table>
		<tr>
			<th>포스터</th>
			<th>순위</th>
			<th>영화</th>
			<th>관객수</th>
		</tr>
		<% 
			int no = 1;
		
			for (Element tr : list) {
				
				String rank = String.format("%02d", no++);
				String title = tr.select(".title > a").text();
				String cumulative = tr.select(".cumulative").text();
				
				Document subdoc = Jsoup.connect("https://www.moviechart.co.kr" + tr.select(".title > a").attr("href")).get();
				
				String src = subdoc.select(".poster > a > img").attr("src");
								
		%>
		<tr>
			<td><img src="https://www.moviechart.co.kr<%= src %>"></td>
			<td><%= rank %></td>
			<td><%= title %></td>
			<td><%= cumulative %></td>
		</tr>
		<% } %>
	</table>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
	</script>
</body>
</html>










