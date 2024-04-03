<%@page import="com.test.jsp.Student"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>
	
</style>
</head>
<body>
	<!-- ex22_el.jsp -->
	<h1>EL</h1>
	
	<%
		
		int a = 10; //자바의 데이터 > 화면 출력
		
		//*** EL은 평범한 출력문이 아니라, 내장 객체의 컬렉션값을 출력하는 전용 출력문이다.
		//pageContext, request, session, application
		
		
		pageContext.setAttribute("b", 20);
		request.setAttribute("c", 30);
		session.setAttribute("d", 40);
		session.setAttribute("c", 300);
		
	%>
	
	<h2>표현식</h2>
	<div>a: <%= a %></div>
	<div>b: <%= pageContext.getAttribute("b") %></div>
	
	<h2>EL</h2>
	<div>a: ${a}</div>
	<div>b: ${pageContext.getAttribute("b")}</div>
	<div>b: ${b}</div>
	<div>c: ${c}</div>
	<div>d: ${d}</div>
	<div>session c: ${sessionScope.c}</div>
	
	<%
		pageContext.setAttribute("n1", 10);
		pageContext.setAttribute("n2", 3);
	%>
	<h2>EL 기능</h2>
	
	<h3>EL 연산 기능</h3>
	
	<div>n1 + 10 = <%= (int)pageContext.getAttribute("n1") + 10 %></div>
	<div>n1 + 10 = ${n1} + 10</div>
	<div>n1 + 10 = ${n1 + 10}</div>
	
	<div>n1 + 10 = ${n1 + 10}</div>
	<div>n1 - 10 = ${n1 - 10}</div>
	<div>n1 * 10 = ${n1 * 10}</div>
	<div>n1 / 10 = ${n1 / 10}</div>
	<div>n1 % 10 = ${n1 % 10}</div>
	<div>n1 mod 10 = ${n1 mod 10}</div>
	
	<div>n1 + n2 = ${n1} + ${n2}</div>
	<div>n1 + n2 = ${n1 + n2}</div>
	
	<hr>
	
	<div>n1 &gt; 0 = ${n1 > 0}</div>
	<div>n1 &lt; 0 = ${n1 < 0}</div>
	<div>n1 &gt; 0 = ${n1 gt 0}</div>
	<div>n1 &lt; 0 = ${n1 lt 0}</div>
	
	<div>n1 &gt;= 0 = ${n1 >= 0}</div>
	<div>n1 &lt;= 0 = ${n1 <= 0}</div>
	<div>n1 &gt;= 0 = ${n1 ge 0}</div>
	<div>n1 &lt;= 0 = ${n1 le 0}</div>
	
	<div>n1 == 0 = ${n1 == 0}</div>
	<div>n1 != 0 = ${n1 != 0}</div>
	<div>n1 == 0 = ${n1 eq 0}</div>
	<div>n1 != 0 = ${n1 ne 0}</div>
	
	<hr>
	
	<div>${true && false}</div>
	<div>${true || false}</div>
	<div>${!true}</div>
	
	<div>${true and false}</div>
	<div>${true or false}</div>
	<div>${not true}</div>
	
	
	<div>${n1 > 0 ? "양수" : "음수" }</div>
	
	<div>${"홍길동".equals("홍길동") }</div>
	<div>${"홍길동" == "홍길동"}</div>
	
	<%
		HashMap<String,String> map = new HashMap<String,String>();
	
		map.put("name", "홍길동");
		map.put("age", "20");
		map.put("address", "서울시");
		
		pageContext.setAttribute("map", map);
	%>
	<h2>HashMap 출력</h2>
	
	<div>이름: <%= map.get("name") %></div>
	<div>나이: <%= map.get("age") %></div>
	<div>주소: <%= map.get("address") %></div>
	
	<div>이름: ${map.get("name")}</div>
	<div>나이: ${map["age"] - 1}</div>
	<div>주소: ${map.address}</div>
	
	<%
		Student s1 = new Student();
	
		s1.setName("아무개");
		s1.setAge(22);
		s1.setAddress("인천시");
		
		request.setAttribute("s1", s1);
	%>
	<h2>객체 출력</h2>
	
	<div>이름: <%= s1.getName() %></div>
	<%-- <div>나이: <%= s1.getAge() %></div> --%>
	<div>주소: <%= s1.getAddress() %></div>
	
	<div>이름: ${s1.getName()}</div>
	<div>나이: ${s1.year}</div>
	<div>주소: ${s1["address"]}</div>
	
	<%
		Student s2 = null;
		pageContext.setAttribute("s2", s2);
		
		String s3 = "";
		pageContext.setAttribute("s3", s3);
	%>
	
	<div><%= s2 == null %></div>
	<div>${s2 == null}</div>
	<div>${empty s2}</div>
	
	<div>${s3 == null}</div>
	<div>${empty s3}</div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
	</script>
</body>
</html>










