package com.test.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.ajax.model.AjaxDAO;
import com.test.ajax.model.CatDTO;

public class Ex09Data extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String catid = req.getParameter("catid");
		String x = req.getParameter("x");
		String y = req.getParameter("y");
		
		AjaxDAO dao = new AjaxDAO();
		
		if (x == null & y == null) {
			dao.addCat(catid);
		} else {
			CatDTO dto = new CatDTO();
			dto.setCatid(catid);
			dto.setX(x);
			dto.setY(y);
			
			dao.editCat(dto);
		}
		
	}
	
}




















