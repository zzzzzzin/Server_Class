package com.test.ajax;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.ajax.model.AjaxDAO;
import com.test.ajax.model.SurveyDTO;

public class Ex01 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Ex01.java
		AjaxDAO dao = new AjaxDAO();
		
		SurveyDTO dto = dao.get(1);
		
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/ex01.jsp");
		dispatcher.forward(req, resp);		
	}
	
}











