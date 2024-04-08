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
	<!-- ex04.jsp -->
	<h1>지도 <small>클릭 이벤트 + 마커 표시</small></h1>
	
	<div>
		<div id="map"></div>
	</div>
	<div class="message">&nbsp;</div>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c0fb77c9349f336937b323fe396a8e90"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	
		var container = document.getElementById('map'); 
		
		var options = {
				center: new kakao.maps.LatLng(37.49932744336939, 127.03332155759142),
			level: 3
		};	
		
		var map = new kakao.maps.Map(container, options);
		
		
		
		kakao.maps.event.addListener(map, 'click', function(evt) {
			//evt == event > 지도 관련 정보 제공
			//alert(evt.latLng);
			
			let msg = `클릭한 위치는 위도(\${evt.latLng.getLat()}), 경도(\${evt.latLng.getLng()})입니다.`;
			$('.message').text(msg);
			
			
			//마커 등록하기
			/*
			const m1 = new kakao.maps.Marker({
				position: new kakao.maps.LatLng(evt.latLng.getLat(), evt.latLng.getLng())
			});
			
			m1.setMap(map);
			*/
			
			//이미지 마커 등록하기
			const path = '/api/images/pointing.png';
			const size = new kakao.maps.Size(64, 64);
			const op = {
				offset: new kakao.maps.Point(0, 22)
			};
			const mImg = new kakao.maps.MarkerImage(path,size,op);
			
			const m1 = new kakao.maps.Marker({
				position: new kakao.maps.LatLng(evt.latLng.getLat(), evt.latLng.getLng())
			});
			
			m1.setImage(mImg);
			m1.setTitle('풍선 도움말');
			m1.setMap(map);
			
		});
		
		
		
		//맵 초기화(마커 등록)
		<c:forEach items="${list}" var="dto" varStatus="status">
		const m${status.count} = new kakao.maps.Marker({
			position: new kakao.maps.LatLng(${dto.lat},${dto.lng})
		});
		m${status.count}.setMap(map);
		</c:forEach>
		
		
		
		//현재 마커 목록 10개 > 자바 스크립트 배열
		const mlist = [];
		let mcount = 0; //지도 상에 보이는 마커 개수
		
		<c:forEach items="${list}" var="dto">
		mlist.push({
			seq: ${dto.seq},
			lat: ${dto.lat},
			lng: ${dto.lng}
		});
		</c:forEach>
		
		
		//지도 이벤트(줌 인/아웃)
		kakao.maps.event.addListener(map, 'zoom_changed', function(evt) {
			
			//$('.message').text(map.getLevel());
			mcount = 0;
			
			$(mlist).each((index, item) => {
				
				if (contains(item)) {
					mcount++;
				}
				
			});
			
			$('.message').text(mcount);
			
		});
		
		function contains(item) {
			
			//현재 맵안에 item이 포함되었는지?
			const sw = map.getBounds().getSouthWest();
			const ne = map.getBounds().getNorthEast();
			
			if (item.lat >= sw.getLat() 
				&& item.lat <= ne.getLat()
				&& item.lng >= sw.getLng()
				&& item.lng <= ne.getLng()) {
				
				return true;
			}
			
			return false;
		}
		
		
	</script>
</body>
</html>










