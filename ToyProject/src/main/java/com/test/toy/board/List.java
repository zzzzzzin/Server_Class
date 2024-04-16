package com.test.toy.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.toy.board.model.BoardDTO;
import com.test.toy.board.repository.BoardDAO;

@WebServlet("/board/list.do")
public class List extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		//조회수 관련
		session.setAttribute("read", "n");
		
		
		//1. DB 작업 > select
		//2. 결과 > 출력
		BoardDAO dao = new BoardDAO();
		
		ArrayList<BoardDTO> list = dao.list();
		
		
		
		//데이터 조작
		for (BoardDTO dto : list) {
			
			//날짜 자르기
			//dto.setRegdate(dto.getRegdate().substring(0, 10));
			
			//제목 자르기
			String subject = dto.getSubject();
			
			if (subject.length() > 20) {
				subject = subject.substring(0, 20) + "..";
			}
			
			//제목 > 태그 > 이스케이프
			subject = subject.replace(">", "&gt;").replace("<", "&lt;");

			dto.setSubject(subject);
			
		}
		
		
		
		req.setAttribute("list", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		dispatcher.forward(req, resp);

	}

}

