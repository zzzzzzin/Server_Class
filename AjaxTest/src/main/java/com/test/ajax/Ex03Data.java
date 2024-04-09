package com.test.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ex03Data extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//DB 작업 > 데이터
		int count = 100;
		
		//resp.sendRedirect("/ajax/ex03.do?count=" + count);
		
		PrintWriter writer = resp.getWriter();
		
		//writer.println("<html>");
		//writer.println("<div>");
		//writer.println("<div>" + count + "</div>");
		//writer.println("</div>");
		//writer.println("</html>");
		
		writer.print(count);
		
		writer.close();
		
	}
}
