package com.test.toy.board.data;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.toy.board.repository.BoardDAO;

@WebServlet("/board/delcomment.do")
public class DelComment extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. 데이터 가져오기(seq)
		//2. DB 작업 > delete 
		//3. 결과
		
		String seq = req.getParameter("seq");
		
		BoardDAO dao = new BoardDAO();
		
		int result = dao.delComment(seq);
		
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		writer.print("{");
		writer.print("\"result\": "+ result); //"result": 1
		writer.print("}");
		writer.close();

	}
}