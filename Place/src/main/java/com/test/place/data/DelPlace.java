package com.test.place.data;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.place.model.PlaceDAO;

@WebServlet("/delplace.do")
public class DelPlace extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		//System.out.println(seq);
		
		PlaceDAO dao = new PlaceDAO();
		
		int result = dao.del(seq);
		
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.print("{");
		writer.print("\"result\":" + result);
		writer.print("}");
		writer.close();

	}
}