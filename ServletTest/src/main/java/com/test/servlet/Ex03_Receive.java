package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ex03_Receive extends HttpServlet {
	
	@Override
	//doGet > 평상시 호출하는 용도(입력 페이지, 정보 페이지 등..)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET 요청");
	}
	
	//doPost > 데이터를 수신 > 처리하는 용도
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//HttpServletRequest req: 클라이언트 > 서버
		//HttpServletResponse resp: 서버 > 클라이언트

		//전송된 데이터를 수신하기
		
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("txtName");
		String age = req.getParameter("txtAge");
		
		
		System.out.println(name);
		System.out.println(age);
		
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<meta charset='UTF-8'>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h1>결과</h1>");
		writer.println("<div>이름: " + name + "</div>");
		writer.println("<div>나이: " + age + "</div>");
		writer.println("<script>location.href='/ServletTest/ex01.do';</script>");
		writer.println("</body>");
		writer.println("</html>");
		
		writer.close();
		

	}

}







