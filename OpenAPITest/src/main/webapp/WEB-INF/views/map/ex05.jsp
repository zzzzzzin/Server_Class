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
	<!-- ex05.jsp -->
	<h1>지도 <small>마커 추가하기</small></h1>
	
	<div>
		<div id="map"></div>
	</div>
	
	<form method="POST" action="/api/add.do">
	<div>
		<input type="submit" value="마커 추가하기">
	</div>
	<input type="hidden" name="lat">
	<input type="hidden" name="lng">
	</form>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c0fb77c9349f336937b323fe396a8e90"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	
		var container = document.getElementById('map'); 
		
		var options = {
				center: new kakao.maps.LatLng(37.49932744336939, 127.03332155759142),
			level: 3
		};	
		
		var map = new kakao.maps.Map(container, options);
		
		let m1 = null;
		
		//마커 추가하기
		kakao.maps.event.addListener(map, 'click', function(evt) {
			
			if (m1 != null) {
				m1.setMap(null);
			}
			
			const path = '/api/images/pointing.png';
			const size = new kakao.maps.Size(64, 64);
			const op = {
				offset: new kakao.maps.Point(0, 25)
			};
			const mImg = new kakao.maps.MarkerImage(path,size,op);
			
			m1 = new kakao.maps.Marker({
				position: evt.latLng 
			});
			
			m1.setImage(mImg);
			m1.setMap(map);
			
			
			$('input[name=lat]').val(evt.latLng.getLat());
			$('input[name=lng]').val(evt.latLng.getLng());
			
		});
		
	</script>
</body>
</html>










