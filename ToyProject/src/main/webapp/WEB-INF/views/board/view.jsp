<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
	
</style>
</head>
<body>
	<!-- view.jsp -->
	<%@include file="/WEB-INF/views/inc/header.jsp" %>
	
	<div id="main">
		
		<h1 class="sub">게시판 <small>상세보기</small></h1>
		
		<table class="vertical" id="view">
			<tr>
				<th>번호</th>
				<td>${dto.seq}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${dto.name}(${dto.id})</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.subject}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${dto.content}</td>
			</tr>
			<c:if test="${not empty dto.attach}">
			<tr>
				<th>장소</th>
				<td><img src="/toy/asset/place/${dto.attach}" id="imgPlace"></td>
			</tr>
			<tr>
				<th>위치</th>
				<td><div id="map"></div></td>
			</tr>
			</c:if>
			<tr>
				<th>날짜</th>
				<td>${dto.regdate}</td>
			</tr>
			<tr>
				<th>읽음</th>
				<td>${dto.readcount}</td>
			</tr>
		</table>
		
		
		<!-- 댓글 보기 -->
		<table id="comment">
			<tbody>
			<c:if test="${clist.size() == 0}">
			<tr>
				<td colspan="2">댓글이 없습니다.</td>
			</tr>
			</c:if>
			<c:forEach items="${clist}" var="cdto">
			<tr>
				<td class="commentContent">
					<div>${cdto.content}</div>
					<div>${cdto.regdate}</div>
				</td>
				<td class="commentInfo">
					<div>
						<div>${cdto.name}(${cdto.id})</div>
						<c:if test="${not empty id && (cdto.id == id || lv == 2)}">
						<div>
							<span class="material-symbols-outlined" onclick="del(${cdto.seq});">delete</span>
							<span class="material-symbols-outlined" onclick="edit(${cdto.seq});">edit_note</span>
						</div>
						</c:if>
					</div>
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		
		
		<div style="text-align: center;">
			<button type="button" class="comment" id="btnMoreComment">댓글 더보기</button>
		</div>
		
		
		<c:if test="${not empty id}">
		<!-- 댓글 쓰기 -->
		<form id="addCommentForm">
		<table id="addComment">
			<tr>
				<td><input type="text" name="content" class="full" required></td>
				<td><button type="button" class="comment" id="btnAddComment">댓글 쓰기</button></td>
			</tr>
		</table>
		</form>
		</c:if>
		
		<div class="group">
			<button type="button" class="back" onclick="location.href='/toy/board/list.do?column=${column}&word=${word}&page=${page}';">돌아가기</button>
			<!-- <button type="button" class="back" onclick="history.back();">돌아가기</button> -->
			
			<c:if test="${not empty id && (dto.id == id || lv == '2')}">
			<button type="button" class="edit" onclick="location.href='/toy/board/edit.do?seq=${dto.seq}';">수정하기</button>
			<button type="button" class="del" onclick="location.href='/toy/board/del.do?seq=${dto.seq}';">삭제하기</button>
			</c:if>
			
			<c:if test="${not empty id}">
			<button type="button" class="reply primary" onclick="location.href='/toy/board/add.do?reply=y&thread=${dto.thread}&depth=${dto.depth}';">답변하기</button>
			</c:if>
			
			
		</div>
		
	</div>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c0fb77c9349f336937b323fe396a8e90&libraries=services"></script>
	<script>
	
		$('#btnAddComment').click(() => {
						
			$.ajax({
				type: 'POST',
				url: '/toy/board/addcomment.do',
				data: {
					content: $('input[name=content]').val(),
					bseq: ${dto.seq}
				},
				dataType: 'json',
				success: function(result) {
					
					if (result.result == '1') {
						
						//alert($('#comment tbody tr:nth-child(1) td').length);
						
						//안내 메시지 삭제
						if ($('#comment tbody tr:nth-child(1)').children().length == 1) {
							$('#comment tbody').html('');	
						}
						
						
						//새로 작성한 댓글을 화면에 반영
						
						let temp = `
							<tr>
								<td class="commentContent">
									<div>\${result.dto.content}</div>
									<div>\${result.dto.regdate}</div>
								</td>
								<td class="commentInfo">
									<div>
										<div>\${result.dto.name}(\${result.dto.id})</div>
										<div>
											<span class="material-symbols-outlined" onclick="del(\${result.dto.seq});">delete</span>
											<span class="material-symbols-outlined" onclick="edit(\${result.dto.seq});">edit_note</span>
										</div>
									</div>
								</td>
							</tr>
						`;
						
						$('#comment tbody').prepend(temp);
						
						$('input[name=content]').val('');
						
						$('#btnMoreComment').show();
						
					} else {
						alert('댓글 등록을 실패했습니다.');
					}
					
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			
		});
		
		//form.onsubmit
		$('#addCommentForm').submit(() => {
			
			$('#btnAddComment').click();
			
			event.preventDefault();
		});
		
		
		function del(seq) {
			
			$('.commentEditRow').remove();
			
			//$(event.target).parents('tr').hide();
			let tr = $(event.target).parents('tr');
			
			if (confirm('삭제하겠습니까?')) {
				
				$.ajax({
					type: 'POST',
					url: '/toy/board/delcomment.do',
					data: 'seq=' + seq,
					dataType: 'json',
					success: function(result) {
						
						if (result.result == 1) {
							
							//alert('삭제 성공');
							tr.remove();
							
						} else{
							alert('댓글 삭제를 실패했습니다.');
						}
						
					},
					error: function(a,b,c) {
						console.log(a,b,c);
					}
				});
				
			}
			
		}
		
		
		
		function edit(seq) {
			
			$('.commentEditRow').remove();
			
			let content = $(event.target).parents('tr').children().eq(0).children().eq(0).text();
			//alert(content);
			
			$(event.target).parents('tr').after(`
			
				<tr class="commentEditRow">
					<td><input type="text" name="content" class="full" required value="\${content}" id="txtComment"></td>
					<td class="commentEdit">
						<span class="material-symbols-outlined" onclick="editComment(\${seq});">edit_square</span>
						<span class="material-symbols-outlined" onclick="$(event.target).parents('tr').remove();">close</span>
					</td>
				</tr>
					
			`);
			
		}
		
		function editComment(seq) {
			//seq, content
			//alert(seq);
			//alert($('#txtComment').val());
			
			let td = $(event.target).parents('tr').prev().children().eq(0).children().eq(0);
			let tr = $(event.target).parents('tr');
			
			//td.text(111);
			
			$.ajax({
				type: 'POST',
				url: '/toy/board/editcomment.do',
				data: {
					seq: seq,
					content: $('#txtComment').val()
				},
				dataType: 'json',
				success: function(result) {
					
					if (result.result == '1') {
						
						//alert('성공');
						td.text($('#txtComment').val());
						tr.remove();
						
					} else {
						alert('댓글 수정을 실패했습니다.');
					}
					
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			
		}
		
		let commentBegin = ${clist.size() + 1};
		
		<c:if test="${clist.size() == 0}">
			$('#btnMoreComment').hide();
		</c:if>
		
		$('#btnMoreComment').click(() => {
			
			//where rnum between 11 and 20;
			//where rnum between 21 and 30;
			$.ajax({
				type: 'GET',
				url: '/toy/board/morecomment.do',
				data: {
					commentBegin: commentBegin,
					bseq: ${dto.seq}
				},
				dataType: 'json',
				success: function(result) {
					
					if (result.length > 0) {

						commentBegin += 10;
						
						result.forEach(obj => {
							
							let temp = `
								<tr>
									<td class="commentContent">
										<div>\${obj.content}</div>
										<div>\${obj.regdate}</div>
									</td>
									<td class="commentInfo">
										<div>
											<div>\${obj.name}(\${obj.id})</div>
							`;
							
							if ('${id}' != '' && (obj.id == '${id}' || ${lv == 2})) {
							
							temp += `		<div>
												<span class="material-symbols-outlined" onclick="del(\${obj.seq});">delete</span>
												<span class="material-symbols-outlined" onclick="edit(\${obj.seq});">edit_note</span>
											</div>`;
							}		
											
							temp += `	</div>
									</td>
								</tr>
							`;
							
							$('#comment tbody').append(temp);
							
						});
						
					} else {
						alert('더 이상 가져올 댓글이 없습니다.');
					}
					
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			
		});
		
		
		<c:if test="${not empty dto.attach}">
		
		const container = document.getElementById('map'); 
		const options = {
				center: new kakao.maps.LatLng(37.49932744336939, 127.03332155759142),
			level: 3
		};	
		const map = new kakao.maps.Map(container, options);
		
		
		const marker = new kakao.maps.Marker({
			position: new kakao.maps.LatLng(${lat}, ${lng})
		});
		const path = '/toy/asset/images/studio.png';
		const size = new kakao.maps.Size(64, 64);
		const op = {};
		const mImg = new kakao.maps.MarkerImage(path,size,op);
		marker.setImage(mImg);
		
		marker.setMap(map);
		map.panTo(new kakao.maps.LatLng(${lat}, ${lng}));
		
		</c:if>
	
	</script>
</body>
</html>










