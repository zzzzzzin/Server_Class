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
	<!-- ex06.jsp -->
	<h1>지도 <small>주소로 좌표 얻기</small></h1>
	
	<div>
		<div id="map"></div>
	</div>
	<hr>
	<div>
		<input type="text" id="address" class="full"
			placeholder="주소를 입력하세요.">
	</div>
		
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=aee9a65a1b49a897184f218db1f2b195&libraries=services"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	
		var container = document.getElementById('map'); 
		
		var options = {
				center: new kakao.maps.LatLng(37.49932744336939, 127.03332155759142),
			level: 3
		};	
		
		var map = new kakao.maps.Map(container, options);
		
		$('#address').keydown(function(evt) {
			if (evt.keyCode == 13) {
				//alert($(this).val());
				
				search($(this).val());
				
			}
		});
		
		let m1 = null;
		
		function search(address) {
			
			const geocoder = new kakao.maps.services.Geocoder();
			
			geocoder.addressSearch(address, function(result, status) {
				
				//alert(result[0].y + "," + result[0].x);
				
				//마커로 위치 출력
				if(status == kakao.maps.services.Status.OK) {
					const p = new kakao.maps.LatLng(result[0].y, result[0].x);
					
					if(m1 != null) {
						m1.setMap(null);
					}
					
					m1 = new kakao.maps.Marker({
						position: p
					});
					
					m1.setMap(map);
					map.panTo(p)
				}
				
			});
			
		}
		
	</script>
</body>
</html>










