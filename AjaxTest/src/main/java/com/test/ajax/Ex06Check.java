package com.test.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.ajax.model.AjaxDAO;

public class Ex06Check extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		
		AjaxDAO dao = new AjaxDAO();
		
		int result = dao.check(id);
		
		PrintWriter writer = resp.getWriter();
		writer.print(result);
		writer.close();
		
	}
	
}








