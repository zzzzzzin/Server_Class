package com.test.place.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.test.place.model.PlaceDAO;
import com.test.place.model.PlaceDTO;

@WebServlet("/listplace.do")
public class ListPlace extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String swLat = req.getParameter("swLat");
		String swLng = req.getParameter("swLng");
		String neLat = req.getParameter("neLat");
		String neLng = req.getParameter("neLng");
		
//		System.out.println(swLat);
//		System.out.println(swLng);
//		System.out.println(neLat);
//		System.out.println(neLng);
		
		HashMap<String,String> map = new HashMap<>();
		map.put("swLat", swLat);
		map.put("swLng", swLng);
		map.put("neLat", neLat);
		map.put("neLng", neLng);
		
		

		PlaceDAO dao = new PlaceDAO();
		
		ArrayList<PlaceDTO> plist = dao.plist(map);
		
		JSONArray arr = new JSONArray();
		
		for (PlaceDTO dto : plist) {
			
			JSONObject obj = new JSONObject(); //장소 1개
			
			obj.put("seq", dto.getSeq());
			obj.put("name", dto.getName());
			obj.put("lat", dto.getLat());
			obj.put("lng", dto.getLng());
			obj.put("description", dto.getDescription());
			obj.put("address", dto.getAddress());
			obj.put("category", dto.getCategory());
			obj.put("marker", dto.getMarker());
			
			arr.add(obj);
			
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		writer.print(arr);
		writer.close();

	}

}









