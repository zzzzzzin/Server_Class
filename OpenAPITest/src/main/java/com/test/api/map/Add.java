package com.test.api.map;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.api.model.MapDAO;
import com.test.api.model.MarkerDTO;

public class Add extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. 데이터 가져오기(lat, lng)
		//2. DB 작업 > insert
		//3. 이동
		
		String lat = req.getParameter("lat");
		String lng = req.getParameter("lng");
		
		MapDAO dao = new MapDAO();
		
		MarkerDTO dto = new MarkerDTO();
		dto.setLat(lat);
		dto.setLng(lng);
		
		dao.add(dto);
		
		resp.sendRedirect("/api/map.do?ex=04");
		
	}
	
}










