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
	
	#place {
		width: 128px;
		height: 128px;
		border: 1px solid black;
		background: gold;
		margin-top: 10px;
	}
	
</style>
</head>
<body>
	<!-- ex09.jsp -->
	
	<h1>고양이 + Ajax</h1>
	
	<div>
		<input type="button" value="고양이 추가하기" id="btnAdd">
		<div id="place"></div>
	</div>
	
	<div id="list"></div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	<script>
	
		let n = 1;
		let m = ${m + 1};
	
		$('#btnAdd').click(()=>{
			
			/* 
			if ($('#place').html() != '') {
				return;
			} 
			*/
			
			$('<img src="/ajax/images/catty' + String(n).padStart(2, 0) + '.png" id="cat' + m + '">')
					.appendTo($('#place'))
					.draggable({
						
						stop: function(event, ui) {
							
							//$('#list').append(this);
							
							//alert(this.id);
							//alert(ui.position.left);
							//alert(ui.position.top);
							
							$.ajax({
								type: 'POST',
								url: '/ajax/ex09data.do',
								data: 'catid=' + this.id + '&x=' + ui.position.left + '&y=' + ui.position.top,
								error: function(a,b,c) {
									console.log(a,b,c);
								}
							});
							
						}
						
					});
			
			
			//고양이 정보 > 서버 전송 > insert
			$.ajax({
				type: 'POST',
				url: '/ajax/ex09data.do',
				data: 'catid=cat' + m,
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			
			
			n++;
			m++;
			if (n > 21) {
				n = 1;
			}
			
		});
		
		
		<c:forEach items="${list}" var="dto">
		
		$('<img src="/ajax/images/catty01.png" id="${dto.catid}">')
				.appendTo('#place')
				.css({
					left: '${dto.x}px',
					top: '${dto.y}px'
				})
				.draggable({
					stop: function(event, ui) {
						
						$.ajax({
							type: 'POST',
							url: '/ajax/ex09data.do',
							data: 'catid=' + this.id + '&x=' + ui.position.left + '&y=' + ui.position.top,
							error: function(a,b,c) {
								console.log(a,b,c);
							}
						});
						
					}
				});
		
		</c:forEach>
		
	
	</script>
</body>
</html>










