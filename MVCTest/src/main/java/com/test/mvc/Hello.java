package com.test.mvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Hello extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//비즈니스 업무 처리 or 데이터베이스 처리
		//화면 제작 > JSP에게 위임
		
		
		System.out.println("Hello");
		
		//resp.sendRedirect("/mvc/hello.jsp");
		
		
		int count = 100;
		
		req.setAttribute("count", count);
		
		//jsp애서 실행시 에러 발생하도록 일부로 작성
		RequestDispatcher dispatcher 
			= req.getRequestDispatcher("/WEB-INF/views/hello.jsp");
		
		dispatcher.forward(req, resp); //pageContext와 동일한 기능
		
		
		
	}
	
}




