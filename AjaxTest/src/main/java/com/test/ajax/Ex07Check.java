package com.test.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.ajax.model.AjaxDAO;
import com.test.ajax.model.SurveyDTO;

public class Ex07Check extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//ex07data.do?type=1 => Text
		//ex07data.do?type=2 => XML
		//ex07data.do?type=3 => JSON
		String type = req.getParameter("type");
		
		if (type.equals("1")) {
			m1(req, resp);
		} else if (type.equals("2")) {
			
		} else if (type.equals("3")) {
			
		}
	
	}

	private void m1(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		//텍스트 변환 + 단일값
		AjaxDAO dao = new AjaxDAO();
		
		SurveyDTO dto = dao.get(1);
		
		//서버가 ajax에게 돌려주는 데이터의 형식을 지정 => 반드시 작성해주기!
		resp.setContentType("text/pain"); //MIME(파일의 형식을 구분)
		resp.setCharacterEncoding("UTF-8");
		
		
		PrintWriter writer = resp.getWriter();
		writer.print(dto.getQuestion());
		writer.close();
		
	}
}






















