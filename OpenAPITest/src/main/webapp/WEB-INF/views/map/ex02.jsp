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
	#map {
		width: 770px;
		height: 400px;
	}
</style>
</head>
<body>
	<!-- ex02.jsp -->
	<h1>지도 <small>기본 코드</small></h1>
	
	<div>
		<div id="map"></div>
	</div>
		
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c0fb77c9349f336937b323fe396a8e90"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	
		var container = document.getElementById('map'); 
		
		//지도 중심
		var options = {
			center: new kakao.maps.LatLng(37.49932744336939, 127.03332155759142),
			level: 3
		};	
		
		var map = new kakao.maps.Map(container, options);
		
	</script>
</body>
</html>










