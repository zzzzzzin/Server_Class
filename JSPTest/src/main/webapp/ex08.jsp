<%@page import="com.test.jsp.MyUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int a = 10;

	//이미 이 구역은 메서드 내부라 메서드/클래스 선언 불가능 => 선언부 등장
	/* 
		public void test() {
		
		}
	*/
	/*
		class Test {
		
		}
	*/
	
	//MyUti의 메서드를 사용하려면 상단에서 선언 + 해당 클래스 임베디드 
	MyUtil util = new MyUtil();

%>    
<%!
	//선언부 => 이 선언부의 코드 적용 범위는 해당 클래스까지
	int b = 20;

	//메서드 사용 가능!
	public int sum(int a, int b) {
		return a + b;
	}
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
</head>
<body>
	<div>10 + 20 = <%= sum(10, 20) %></div>
	<div>30 + 40 = <%= sum(30, 40) %></div>
	<div>50 + 60 = <%= util.sum(50, 60) %></div>
</body>
</html>