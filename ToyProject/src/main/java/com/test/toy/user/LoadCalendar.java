package com.test.toy.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.test.toy.user.repository.UserDAO;

@WebServlet("/user/loadcalendar.do")
public class LoadCalendar extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String year = req.getParameter("year");
		String month = req.getParameter("month");
		String id = session.getAttribute("id").toString();
		
		HashMap<String,String> map = new HashMap<>();
		map.put("year", year);
		map.put("month", month);
		map.put("id", id);
		
		UserDAO dao = new UserDAO();
		
		ArrayList<HashMap<String,String>> result = dao.loadCalendar(map);
		
		resp.setContentType("application/json");
		
		JSONArray arr = new JSONArray();
		
		for (HashMap<String,String> m : result) {
			
			JSONObject obj = new JSONObject();
			
			obj.put("regdate", m.get("regdate"));
			obj.put("cnt", m.get("cnt"));
			obj.put("bcnt", m.get("bcnt"));
			obj.put("ccnt", m.get("ccnt"));
			
			arr.add(obj);
			
		}
		
		PrintWriter writer = resp.getWriter();
		writer.print(arr);
		writer.close();
		

	}
}









