package com.test.ajax;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.ajax.model.AjaxDAO;
import com.test.ajax.model.CatDTO;

public class Ex09 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//고양이 id의 최대값?
		AjaxDAO dao = new AjaxDAO();
		
		String m = dao.getNum();
		
		ArrayList<CatDTO> list = dao.listCat();
		
		
		
		req.setAttribute("m", m);	
		req.setAttribute("list", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/ex09.jsp");
		dispatcher.forward(req, resp);
	}
	
}







