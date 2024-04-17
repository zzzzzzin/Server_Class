package com.test.toy.board.data;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.test.toy.board.model.CommentDTO;
import com.test.toy.board.repository.BoardDAO;

@WebServlet("/board/addcomment.do")
public class AddComment extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. 데이터 가져오기(content, bseq)
		//2. DB 작업 > insert
		//3. 결과
		
		HttpSession session = req.getSession();
		
		String content = req.getParameter("content");
		String bseq = req.getParameter("bseq");
		String id = session.getAttribute("id").toString();
		
		BoardDAO dao = new BoardDAO();
		
		CommentDTO dto = new CommentDTO();
		dto.setContent(content);
		dto.setBseq(bseq);
		dto.setId(id);
		
		int result = dao.addComment(dto);
		
		//방금 작성한 댓글 가져오기
		CommentDTO dto2 = dao.getComment();
		
		resp.setContentType("application/json");
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		JSONObject subObj = new JSONObject();
		subObj.put("seq", dto2.getSeq());
		subObj.put("content", dto2.getContent());
		subObj.put("name", dto2.getName());
		subObj.put("id", dto2.getId());
		subObj.put("regdate", dto2.getRegdate());
		
		obj.put("dto", subObj);
		
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.print(obj);
		writer.close();

	}

}













