package com.test.api.map;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.api.model.MapDAO;
import com.test.api.model.MarkerDTO;

public class Ex01 extends HttpServlet {
	
	//서블릿 1개 <-> JSP N개
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//map.do
		//map.do?ex=01
		//map.do?ex=02
		
		String ex = req.getParameter("ex");
		
		if (ex == null) {
			ex = "01";
		}
		
		if (ex.equals("04")) {
			
			MapDAO dao = new MapDAO();
			ArrayList<MarkerDTO> list = dao.list();
			req.setAttribute("list", list);
			
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/map/ex" + ex + ".jsp");
		dispatcher.forward(req, resp);
		
	}
	
}




