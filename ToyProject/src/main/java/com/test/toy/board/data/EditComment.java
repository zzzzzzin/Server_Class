package com.test.toy.board.data;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.toy.board.model.CommentDTO;
import com.test.toy.board.repository.BoardDAO;

@WebServlet("/board/editcomment.do")
public class EditComment extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. 데이터 가져오기(seq, content)
		//2. DB 작업 > update
		//3. 결과
		
		String seq = req.getParameter("seq");
		String content = req.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
		
		CommentDTO dto = new CommentDTO();
		dto.setSeq(seq);
		dto.setContent(content);
		
		int result = dao.editComment(dto);
		
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		writer.print("{");
		writer.print("\"result\": " + result); //"result": 1
		writer.print("}");
		writer.close();

	}

}










