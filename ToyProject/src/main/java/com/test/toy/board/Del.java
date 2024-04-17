package com.test.toy.board;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.toy.board.model.BoardDTO;
import com.test.toy.board.repository.BoardDAO;
import com.test.util.OutputUtil;

@WebServlet("/board/del.do")
public class Del extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (Auth.check(req, resp)) {
			return;
		}
		
		//1. 데이터 가져오기(seq)
		//2. 전달
		
		String seq = req.getParameter("seq");
		
		req.setAttribute("seq", seq);		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/del.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 데이터 가져오기(seq)
		//2. DB 작업 > delete
		//3. 결과
		
		String seq = req.getParameter("seq");
		
		BoardDAO dao = new BoardDAO();
		
		//첨부 파일 삭제
		BoardDTO dto = dao.get(seq);
		
		if (dto.getAttach() != null) {
			File file = new File(req.getRealPath("/asset/place") + "/" + dto.getAttach());
			file.delete();
		}
		
		
		int result = dao.del(seq);
		
		if (result == 1) {
			resp.sendRedirect("/toy/board/list.do");
		} else {
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.print(OutputUtil.redirect("실패했습니다."));
			writer.close();
		}
		
	}

}











