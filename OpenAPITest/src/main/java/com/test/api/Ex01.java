package com.test.api;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.api.model.BookDAO;
import com.test.api.model.BookDTO;


//MVC > Model(데이터) + View(출력) + Controller(통제)
//Controller > 업무 위임 > BookDAO > Model(Service)
//					     > ex01.jsp > 화면 구현 > View
public class Ex01 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청 상황 2가지 => doGET의 요청을 구분해야함
		//1. 처음 요청(URL) > ex01.do
		//2. 검색 요청(<form>) > ex01.do?word=자바
		
		//System.out.println("호출");
		
		//doGET은 인코딩 필요 없음
		String word = req.getParameter("word");
		
		//System.out.println(word == null);
		//System.out.println(word.equals(""));
		
		if (word != null) {
			//검색 중..
			
			BookDAO dao = new BookDAO();
			
			//검색어 word를 BookDAO에게 BookDTO의 ArrayList로 포장해서 전달 -> 화면에 출력
			ArrayList<BookDTO> list = dao.search(word);
			
			req.setAttribute("list", list);
			req.setAttribute("word", word);
			
		}
		
		
		
		RequestDispatcher dispatcher 
			= req.getRequestDispatcher("WEB-INF/views/ex01.jsp");
		dispatcher.forward(req, resp);
	}
}
