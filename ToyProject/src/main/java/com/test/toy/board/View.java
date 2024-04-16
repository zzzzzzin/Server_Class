package com.test.toy.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.toy.board.model.BoardDTO;
import com.test.toy.board.repository.BoardDAO;

@WebServlet("/board/view.do")
public class View extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		 HttpSession session = req.getSession();
		 
		//1. 데이터 가져오기(seq)
		//2. DB 작업 > select .. where seq = 10
		//3. 결과 > 출력
		
		String seq = req.getParameter("seq");
		
		BoardDAO dao = new BoardDAO();
		
		if (session.getAttribute("read") != null 
				&& session.getAttribute("read").toString().equals("n")) {
			//조회수 증가
			dao.updateReadcount(seq);
			session.setAttribute("read", "y");
		}
		
		
		
		//게시물 가져오기
		BoardDTO dto = dao.get(seq);
		
		
		//데이터 조작
		String content = dto.getContent();
		
		//글내용 > 태그 > 이스케이프
		content = content.replace(">", "&gt;").replace("<", "&lt;");

		//글내용 > 개행 문자 처리
		content = content.replace("\r\n", "<br>");
		
		dto.setContent(content);
		
		String subject = dto.getSubject();
		
		//제목 > 태그 > 이스케이프
		subject = subject.replace(">", "&gt;").replace("<", "&lt;");

		dto.setSubject(subject);

		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
		dispatcher.forward(req, resp);

	}

}

