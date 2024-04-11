package com.test.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.test.ajax.model.AjaxDAO;
import com.test.ajax.model.ZipcodeDTO;

public class Ex08Data extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		
		String dong = req.getParameter("dong");
		
		AjaxDAO dao = new AjaxDAO();
		
		ArrayList<ZipcodeDTO> list = dao.searchZipcode(dong);
		
		
		/*
			[
				{
					"seq": 5,
					"sido": "서울시",
					"gugun": "강남구",
					"dong": "역삼동",
					"bunji": "110",
					"zipcode": "123-456"
				}
				,
				{
					"seq": 5,
					"sido": "서울시",
					"gugun": "강남구",
					"dong": "역삼동",
					"bunji": "110",
					"zipcode": "123-456"
				}
			]
		
		*/
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		JSONArray arr = new JSONArray();
		
		for (ZipcodeDTO dto : list) {
						
			//ZipcodeDTO > JSON Object
			JSONObject obj = new JSONObject();
			
			obj.put("seq", dto.getSeq());
			obj.put("sido", dto.getSido());
			obj.put("gugun", dto.getGugun());
			obj.put("bunji", dto.getBunji() != null ? dto.getBunji() : "");
			obj.put("dong", dto.getDong());
			obj.put("zipcode", dto.getZipcode());
			
			arr.add(obj);
			
		}
		
		System.out.println(arr.toString());
		
		PrintWriter writer = resp.getWriter();
		writer.print(arr.toString());
		writer.close();
		
	}
	
}







