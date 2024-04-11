package com.test.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.test.ajax.model.AddressDTO;
import com.test.ajax.model.AjaxDAO;

public class Ex10Add extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		
		AjaxDAO dao = new AjaxDAO();
		
		AddressDTO dto = new AddressDTO();
		dto.setName(name);
		dto.setAge(age);
		dto.setGender(gender);
		dto.setAddress(address);
		
		int result = dao.addAddress(dto);
		String seq = dao.getMaxSeq();
		
		resp.setContentType("application/json");
		
		/*
		{
			"result": 1,
			"seq": 7
		}
		*/
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		obj.put("seq", seq);
		
		
		PrintWriter writer = resp.getWriter();
		
		writer.print(obj);
		
//		writer.print("{");
//		writer.printf("\"result\":" + result + ",");
//		writer.printf("\"seq\":" + seq);
//		writer.print("}");
		
		writer.close();
		
	}
	
}







