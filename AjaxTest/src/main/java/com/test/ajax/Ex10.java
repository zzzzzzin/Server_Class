package com.test.ajax;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.ajax.model.AddressDTO;
import com.test.ajax.model.AjaxDAO;

public class Ex10 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		AjaxDAO dao = new AjaxDAO();
		
		ArrayList<AddressDTO> list = dao.listAddress();
		
		req.setAttribute("list", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/ex10.jsp");
		dispatcher.forward(req, resp);
	}
	
}








