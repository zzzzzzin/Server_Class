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
	#list th:nth-child(1) { width: 50px; }
	#list th:nth-child(2) { width: 70px; }
	#list th:nth-child(3) { width: 60px; }
	#list th:nth-child(4) { width: 60px; }
	#list th:nth-child(5) { width: auto; }
	#list td { text-align: center; }
	#list td:nth-child(5) { text-align: left; }
	
	#list span {
		font-size: 1.4rem;
		cursor: pointer;
		float: right;
	}
</style>
</head>
<body>
	<!-- ex10.jsp -->
	<h1>주소록 <small>Ajax</small></h1>
	
	<table id="list">
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
				<th>주소</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.seq}</td>
				<td>${dto.name}</td>
				<td>${dto.age}</td>
				<td>${dto.gender == 'm' ? '남자' : '여자'}</td>
				<td>
					${dto.address}
					<span onclick="edit();">edit</span>
					<span onclick="del(${dto.seq});">&times;</span>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<hr>
	
	<form id="form2">
	<div>
		<label>이름: </label>
		<input type="text" name="name" id="name">
	</div>
	<div>
		<label>나이: </label>
		<input type="number" name="age" id="age">
	</div>
	<div>
		<label>성별: </label>
		<select name="gender" id="gender">
			<option value="m">남자</option>
			<option value="f">여자</option>
		</select>
	</div>
	<div>
		<label>주소: </label>
		<input type="text" name="address" id="address">
	</div>
	<div><input type="button" value="추가하기" id="btn2"></div>
	</form>
	
	<hr>
	
	<!-- <h1>데이터 전송하기</h1>
	
	<form id="form1">
	<table class="vertical">
		<tr>
			<th>숫자</th>
			<td><input type="text" name="num1" id="num1"></td>
		</tr>
		<tr>
			<th>숫자</th>
			<td><input type="text" name="num2" id="num2"></td>
		</tr>
		<tr>
			<th>숫자</th>
			<td><input type="text" id="num3"></td>
		</tr>
	</table>
	<div>
		<input type="button" value="보내기" id="btn1">
	</div>
	</form> -->
	
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	<script>
	
		$('#btn1').click(() => {
			
			//직렬화
			//- 객체를 일련의 한줄의 문자열로 만드는 작업
			
			//num1=100&num2=200&num3=300
			//num1=100&num2=&num3=300
			//num1=111&num2=222
			//alert($('#form1').serialize());
			
			
			$.ajax({
				type: 'POST',
				url: '/ajax/ex10data.do',
				
				//data: 'num1=' + $('#num1').val() + '&num2=' + $('#num2').val() + '&num3=' + $('#num3').val(),
				
				//data: $('#form1').serialize(),
				
				data: {
					num1: $('#num1').val(),
					num2: $('#num2').val(),
					num3: $('#num3').val(),
					id: 'hong',
					color: 'yellow'
				},
				
				error: function(a,b,c) {
					console.log(a,b,c);
				}
				
			});
			
		});
		
		
		$('#btn2').click(()=>{
			
			$.ajax({
				type: 'POST',
				url: '/ajax/ex10add.do',
				data: $('#form2').serialize(),
				dataType: 'json',
				success: function(result) {
					//alert(result.result);
					
					if (result.result == '1') {
						
						
						
						//테이블에 방금 추가한 항목을 동적으로 추가하기
						$('#list tbody').append(`
						
							<tr>
								<td>\${result.seq}</td>
								<td>\${$('#name').val()}</td>
								<td>\${$('#age').val()}</td>
								<td>\${$('#gender').val() == 'm' ? '남자' : '여자'}</td>
								<td>
									\${$('#address').val()}
									<span onclick="del(\${result.seq});">&times;</span>
								</td>
							</tr>
								
						`);
						
						$('#name').val('');
						$('#age').val('');
						$('#address').val('');
						$('#gender').val('m');
						
						
					} else {
						alert('failed');
					}
					
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			
		});
		
		$('#address').keydown(()=>{
			if (event.keyCode == 13) {
				$('#btn2').click();
				$('#address').blur();
			}
		});
		
		function del(seq) {
			//alert(seq);
			
			//lert(this); //<span>
			let tr = $(event.target).parent().parent();
			console.log(event.target);
			
			$.ajax({
				type: 'POST',
				url: '/ajax/ex10del.do',
				data: 'seq=' + seq,
				dataType: 'json',
				success: function(result) {
					
					if (result.result == '1') {
						
						//삭제 성공 > tr 삭제
						//console.log(this);
						//$(this).parent().parent().remove();
						console.log(tr);
						tr.remove();
						
					} else {
						alert('failed');
					}
					
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			
		}
		
		function edit() {
			
			const td2 = $(event.target).parent().parent().children().eq(1);
			const name = td2.text();
			//alert(name);
			
			td2.html('<input type="text" class="short" value="' + name + '">');
			
			const td3 = $(event.target).parent().parent().children().eq(2);
			const age = td3.text();
			
			td3.html('<input type="text" class="short" value="' + age + '">');
			
		}
	
	</script>
</body>
</html>










