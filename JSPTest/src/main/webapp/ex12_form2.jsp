<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<script src="https://kit.fontawesome.com/7121714adf.js"></script>
<style>
	#borderbox {
		display: none;
	}
	#borderbox > div {
		margin-bottom: 5px;
	}
</style>
</head>
<body>
	<form method="POST" action="ex12_ok2.jsp">
	<h1>버튼 만들기</h1>
	<table>
		<tr>
			<th>너비(px)</th>
			<td><input type="number" min="20" max="300" step="10" name="width"></td>
		</tr>
		<tr>
			<th>높이(px)</th>
			<td><input type="number" min="10" max="200" step="10" name="height"></td>
		</tr>
		<tr>
			<th>텍스트</th>
			<td><input type="text" name="txt1"></td>
		</tr>
		<tr>
			<th>배경색</th>
			<td><input type="color" name="bgcolor"></td>
		</tr>
		<tr>
			<th>글자색</th>
			<td><input type="color" name="fontcolor"></td>
		</tr>
		<tr>
			<th>글자 크기(px)</th>
			<td><input type="color" name="fontsize" min="1" max="30"></td>
		</tr>
		<tr>
			<th>버튼 개수(ea)</th>
			<td><input type="number" min="1" max="30" name="nums"></td>
		</tr>
		<tr>
			<th>버튼간격</th>
			
			<td>
				<div>
					좌우 간격: <input type="range" min="0" max="50" name="lr">
				</div>
				<div>
					상하 간격: <input type="range" min="0" max="50" name="td">
				</div>				
			</td>
		</tr>
		<tr>
			<th>아이콘</th>
			<td>
				<input type="radio" name="icon" value="none" checked> 없음
				<input type="radio" name="icon" value="fa-solid fa-house"> <i class="fa-solid fa-house"></i>
				<input type="radio" name="icon" value="fa-solid fa-image"> <i class="fa-solid fa-image"></i></span>
				<input type="radio" name="icon" value="fa-solid fa-location-dot"> <i class="fa-solid fa-location-dot"></i></span>
				<input type="radio" name="icon" value="fa-brands fa-github"> <i class="fa-brands fa-github"></i>
				<input type="radio" name="icon" value="fa-solid fa-paperclip"> <i class="fa-solid fa-paperclip"></i>
			</td>
		</tr>
		<tr>
			<th>테두리</th>
			<td>
				<div>
					<select name="isborder" id="isborder" class="form-control sm">
						<option value="n">감추기</option>
						<option value="y">보이기</option>
					</select>
				</div>
				<div id="borderbox">
					<div>두께(px) : <input type="number" name="borderwidth" min="0" max="10" value="1" class="form-control sm inline right"></div>
					<div>색상 : <input type="color" name="bordercolor" value="#000000" class="form-control sm"></div>
					<div>
						스타일 :
						<select name="borderstyle" class="form-control sm inline">
							<option value="solid">실선</option>
							<option value="dashed">쇄선</option>
							<option value="dotted">점선</option>
						</select>	
					</div>
					<div>
						모서리(px) : <input type="number" name="borderradius" min="0" max="50" value="0" class="form-control sm inline right">
					</div>
				</div>
			</td>
		</tr>
		</table>
		<div>
			<input type="submit" value="만들기">
		</div>
	</form>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		$("#isborder").change(function() {
			if ($(this).val() == "y") {
				$("#borderbox").show();
			} else {
				$("#borderbox").hide();
			}
		});
	</script>
</body>
</html>




