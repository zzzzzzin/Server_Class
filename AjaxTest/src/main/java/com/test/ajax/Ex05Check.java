package com.test.ajax;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.ajax.model.AjaxDAO;

public class Ex05Check extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. ex05check.do
		//2. ex05check.do?id=hong
		String id = req.getParameter("id");
		
		if (id != null) {
			
			AjaxDAO dao = new AjaxDAO();

			int result = dao.check(id);	//1(사용불가), 0(사용가능)
			
			req.setAttribute("result", result);
			req.setAttribute("id", id);
			
			
			
		}
		
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/ex05check.jsp");
		dispatcher.forward(req, resp);
	}

}








