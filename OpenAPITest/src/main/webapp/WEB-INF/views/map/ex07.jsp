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
	<!-- ex07.jsp -->
	<h1>지도 <small>좌표를 통해서 주소를 얻기</small></h1>
	
	<div>
		<div id="map"></div>
	</div>
	<div class="message"></div>
		
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=aee9a65a1b49a897184f218db1f2b195&libraries=services"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	
		var container = document.getElementById('map'); 
		
		var options = {
				center: new kakao.maps.LatLng(37.49932744336939, 127.03332155759142),
			level: 3
		};	
		
		var map = new kakao.maps.Map(container, options);
		
		
		let m1 = null;
		
		kakao.maps.event.addListener(map, 'click', function(evt) {
			
			$('.message').html('');
			$('.message').append('<div>클릭한 좌표는 (' + evt.latLng.getLat() + ',' + evt.latLng.getLng() + ')입니다.</div>');
			
			if (m1 != null) {
				m1.setMap(null);
			}
			
			const path = '/api/images/default.png';
			const size = new kakao.maps.Size(64, 64);
			const op = { };
			const mImg = new kakao.maps.MarkerImage(path,size,op);
			
			m1 = new kakao.maps.Marker({ position: evt.latLng });
			m1.setImage(mImg);
			m1.setMap(map);
			
			
			
			const geocoder = new kakao.maps.services.Geocoder();
			
			geocoder.coord2Address(evt.latLng.getLng(), evt.latLng.getLat()
									, function(result, status) {
				
				if (status == kakao.maps.services.Status.OK) {
					
					$('.message').append('주소: ' + result[0].address.address_name);
					
				} else {
					alert('해당 위치의 주소를 찾지 못했습니다.');
				}
				
			});
			
			
			
		});
		
		
	</script>
</body>
</html>










