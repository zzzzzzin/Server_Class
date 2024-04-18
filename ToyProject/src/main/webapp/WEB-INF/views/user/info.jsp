<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
	
	#tblCalendar {
	
	}
	
	#tblCalendar th { width: 108px; }
	#tblCalendar td { 
		height: 90px;
		position: relative; 
	}	
	#tblCalendar .no {
		position: absolute;
		right: 10px;
		top: 10px;
		font-size: 14px;
	}
	#tblCalendar td:first-child .no {
		color: tomato;
	}
	#tblCalendar td:last-child .no {
		color: cornflowerblue;
	}
	#tblCalendar div {
		margin-top: 15px;
	}
	#tblCalendar div > span {
		border-radius: 50%;
		display: inline-block;
		width: 25px;
		height: 20px;
		text-align: center;
		padding-top: 5px;
		font-size: 12px;
		font-weight: bold;
		margin-right: 2px;
	}
	#tblCalendar div > .lcnt {
		background: tomato;
		color: white;
	}
	#tblCalendar div > .bcnt {
		background: cornflowerblue;
		color: white;
	}
	#tblCalendar div > .ccnt {
		background: gold;
	}
	
	
	#titleCalendar {
		display: flex;
		justify-content: space-between;
		margin-top: 50px;
		width: 736px;
		user-select: none;
	}
	
	#titleCalendar span:first-child span:last-child {
		font-size: 1rem;
	}
	
	#titleCalendar .material-symbols-outlined {
		cursor: pointer;
	}
	#titleCalendar .material-symbols-outlined:hover {
		color: cornflowerblue;
	}
	
</style>
</head>
<body>
	<!-- info.jsp -->
	<%@include file="/WEB-INF/views/inc/header.jsp" %>
	
	<div id="main">
		
		<h1 class="sub">회원 <small>정보보기</small></h1>
		
		<table id="info">
			<tr>
				<td rowspan="3"><img src="/toy/asset/pic/${dto.pic}"></td>
				<th>이름</th>
				<td>${dto.name}</td>
				<th>아이디</th>
				<td>${dto.id}</td>
			</tr>
			<tr>
				<th>등급</th>
				<td>${dto.lv == '1' ? '일반회원' : '관리자'}</td>
				<th>이메일</th>
				<td>${dto.email}</td>
			</tr>
			<tr>
				<td colspan="4">${dto.intro}</td>
			</tr>
		</table>
		
		<h2 id="titleCalendar">
			<span>
				<span>활동 내역</span>
				<span>2024.04</span>
			</span>
			<span>
				<span class="material-symbols-outlined" id="btnPrev">skip_previous</span>
				<span class="material-symbols-outlined" id="btnNow">today</span>
				<span class="material-symbols-outlined" id="btnNext">skip_next</span>
			</span>
		</h2>
		
		<table id="tblCalendar">
			<thead>
				<tr>
					<th>SUN</th>
					<th>MON</th>
					<th>TUE</th>
					<th>WEB</th>
					<th>THU</th>
					<th>FRI</th>
					<th>SAT</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		
	</div>
	
	<script>
	
		let year = 0;
		let month = 0;
		
		//이번달
		let now = new Date();
		year = now.getFullYear();
		month = now.getMonth();
		
		function create(year, month) {
			
			$('#titleCalendar span:first-child span:last-child').text(year + '.' + String(month + 1).padStart(2, '0'));
			
			let date = new Date(year, month, 1);
			
			//해당월의 마지막 날
			let lastDate = (new Date(year, month + 1, 0)).getDate();
						
			//해당월 1일의 요일
			let firstDay = date.getDay();
			
			
			
			let temp = '';
			temp += '<tr>';
			
			//1일의 앞부분을 비워두기(요일에 따라)
			for (let i=0; i<firstDay; i++) {
				temp += '<td></td>';
			}
			
			//날짜 <td>
			for (let i=1; i<=lastDate; i++) {
				temp += '<td>';
				temp += '<span class="no" date="' + i + '">' + i + '</span>';
				temp += '<div>';
				/*
				temp += '<span class="lcnt">1</span>';
				temp += '<span class="bcnt">1</span>';
				temp += '<span class="ccnt">1</span>';
				*/
				temp += '</div>';
				temp += '</td>';
				
				if ((i + firstDay) % 7 == 0) {
					temp += '</tr><tr>';
				}
				
			}
			
			let seed = 7;
			if (7 - (lastDate % 7 + firstDay) < 0) {
				seed = 14;
			}
			
			for (let i=0; i<(seed - (lastDate % 7 + firstDay)); i++) {
				temp += '<td></td>';
			}
			
			
			temp += '</tr>';
			
			$('#tblCalendar tbody').html(temp);
			
		}
		
		create(year, month);
		
		$('#btnPrev').click(()=>{
			now.setMonth(now.getMonth() - 1);
			year = now.getFullYear();
			month = now.getMonth();
			create(year, month);
			loadCalendar(year, month);
		});
		
		$('#btnNext').click(()=>{
			now.setMonth(now.getMonth() + 1);
			year = now.getFullYear();
			month = now.getMonth();
			create(year, month);
			loadCalendar(year, month);
		});
		
		$('#btnNow').click(()=>{
			now = new Date();
			year = now.getFullYear();
			month = now.getMonth();
			create(year, month);
			loadCalendar(year, month);
		});
		
		
		function loadCalendar(year, month) {
			month++;
			
			$.ajax({
				type: 'GET',
				url: '/toy/user/loadcalendar.do',
				data: {
					year: year,
					month: month
				},
				dataType: 'json',
				success: function(result) {
					
					//console.log(result);
					
					result.forEach(item => {
						//item.regdate //2024-04-01
						//item.cnt
						//item.bcnt
						//item.ccnt
						//console.log(parseInt(item.regdate.split('-')[2]));
						let date = parseInt(item.regdate.split('-')[2]);
						
						$('#tblCalendar span[date=' + date + '] + div').append('<span class="lcnt">' + item.cnt + '</span>');
						
						if(item.bcnt > 0) {
						$('#tblCalendar span[date=' + date + '] + div').append('<span class="bcnt">' + item.bcnt + '</span>');
						}
						
						if (item.ccnt > 0) {
						$('#tblCalendar span[date=' + date + '] + div').append('<span class="ccnt">' + item.ccnt + '</span>');
						}
					});
					
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			
		}
		
		loadCalendar(year, month);
		
	
	</script>
</body>
</html>










