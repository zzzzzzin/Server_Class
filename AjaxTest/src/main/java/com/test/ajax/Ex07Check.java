package com.test.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.ajax.model.AjaxDAO;
import com.test.ajax.model.SurveyDTO;
import com.test.ajax.model.UserDTO;

public class Ex07Check extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//ex07data.do?type=1
		
		String type = req.getParameter("type");
		
		if (type.equals("1")) {
			m1(req, resp);
		} else if (type.equals("2")) {
			m2(req, resp);
		} else if (type.equals("3")) {
			m3(req, resp);
		} else if (type.equals("4")) {
			m4(req, resp);
		} else if (type.equals("5")) {
			m5(req, resp);
		} else if (type.equals("6")) {
			m6(req, resp);
		}
		
		
	}
	
	private void m6(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
		
		//JSON 반환 + 다중값
				//- select * from tblUser;
				
				AjaxDAO dao = new AjaxDAO();
				
				ArrayList<UserDTO> list = dao.listUser();
				
				/*
				
				{
					"question": "질문"
				}
				
				[
					{
						"id": "hong",
						"pw": "1111",
						"name": "홍길동",
						"lv": 1
					}
					,
					{
						"id": "hong",
						"pw": "1111",
						"name": "홍길동",
						"lv": 1
					}
				]
				
			*/
				
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				
				PrintWriter writer = resp.getWriter();
				
				writer.println("[");
				
				for (UserDTO dto : list) {
					writer.println("{");
						writer.printf("\"id\": \"%s\",", dto.getId());
						writer.printf("\"pw\": \"%s\",", dto.getPw());
						writer.printf("\"name\": \"%s\",", dto.getName());
						writer.printf("\"lv\": %s", dto.getLv());
					writer.println("},");
				}
				
				writer.println("]");
				
				writer.close();
	}
	
	private void m5(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
		
		AjaxDAO dao = new AjaxDAO();
		
		SurveyDTO dto = dao.get(1);
		
		/*
			JSON, JavaScript Object Natation
			- 객체 표기법
			- 문자열 > 플랫폼이나 언어에 독립적이다.
			- 쌍따옴표
			- 메소드 구현 불가능
			
			//단일 데이터라도 {}로 묶기
			{
				"question": "언어는?"
			}
		*/
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		
		writer.println("{");
		writer.printf("\"question\": \"%s\"", dto.getQuestion());
		writer.println("}");
		
		
		writer.close();
	}
	
	private void m4(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
		
		/*
			<?xml version="1.0" encoding="UTF-8"?> //xml 선언부
			<list>
				<user>
					<id>hong</id>
					<pw>1111</pw>
					<name>홍길동</name>
					<lv>1</lv>
				</user>
				<user>
					<id>hong</id>
					<pw>1111</pw>
					<name>홍길동</name>
					<lv>1</lv>
				</user>
			</list>
		*/		
		AjaxDAO dao = new AjaxDAO();
		
		ArrayList<UserDTO> list = dao.listUser();
		
		resp.setContentType("text/xml");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		writer.println("<list>");
		
		for (UserDTO dto : list) {
			writer.println("<user>");
			writer.printf("<id>%s</id>", dto.getId());
			writer.printf("<pw>%s</pw>", dto.getPw());
			writer.printf("<name>%s</name>", dto.getName());
			writer.printf("<lv>%s</lv>", dto.getLv());
			writer.println("</user>");
		}

		writer.println("</list>");
		
		
		writer.close();
		
		
	}

	private void m3(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
		
		//XML 반환 + 단일값
		
		AjaxDAO dao = new AjaxDAO();
		
		SurveyDTO dto = dao.get(1);
		
		/*
			
			Well-formed XML
			
			<?xml version="1.0" encoding="UTF-8"?> //xml 선언부
			<result>가장 좋아하는 프로그래밍 언어는?</result>
			
		*/
		
		
		resp.setContentType("text/xml");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		writer.printf("<result>%s</result>", dto.getQuestion());
		
		writer.close();
		
	}

	private void m2(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//텍스트 반환 + 다중값
		//- select * from tblUser;
		
		AjaxDAO dao = new AjaxDAO();
		
		ArrayList<UserDTO> list = dao.listUser();
		
//		hong,1111,홍길동,1
//		dog,1111,강아지,1
//		cat,1111,고양이,2
		
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		
		for (UserDTO dto : list) {
			writer.printf("%s,%s,%s,%s\r\n"
							, dto.getId()
							, dto.getPw()
							, dto.getName()
							, dto.getLv()
							);
		}
		
		writer.close();
		
	}

	private void m1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//텍스트 반환 + 단일값
		AjaxDAO dao = new AjaxDAO();
		
		SurveyDTO dto = dao.get(1);
		
		
		//서버가 ajax에게 돌려주는 데이터의 형식을 지정한다.(***)
		//- 응답 헤더 설정
		resp.setContentType("text/plain"); //MIME
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		writer.print(dto.getQuestion());
		writer.close();
		
	}
	
}















