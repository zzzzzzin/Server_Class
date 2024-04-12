package com.test.place.data;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.place.model.PlaceDAO;
import com.test.place.model.PlaceDTO;

@WebServlet("/addplace.do")
public class AddPlace extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String category = req.getParameter("category");
		String lat = req.getParameter("lat");
		String lng = req.getParameter("lng");
		String address = req.getParameter("address");
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		
		PlaceDAO dao = new PlaceDAO();
		
		PlaceDTO dto = new PlaceDTO();
		
		dto.setCategory(category);
		dto.setLat(lat);
		dto.setLng(lng);
		dto.setAddress(address);
		dto.setName(name);
		dto.setDescription(description);
		
		int result = dao.add(dto);
		
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		writer.print("{");
		writer.print("\"result\":" + result);
		writer.print("}");
		writer.close();

	}

}



















