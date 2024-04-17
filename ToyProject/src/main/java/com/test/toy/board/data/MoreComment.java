package com.test.toy.board.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.test.toy.board.model.CommentDTO;
import com.test.toy.board.repository.BoardDAO;

@WebServlet("/board/morecomment.do")
public class MoreComment extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String commentBegin = req.getParameter("commentBegin");
		String bseq = req.getParameter("bseq");
		
		BoardDAO dao = new BoardDAO();
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("commentBegin", commentBegin);
		map.put("bseq", bseq);
		
		ArrayList<CommentDTO> list = dao.listMoreComment(map);
		
		resp.setContentType("application/json");
		
		JSONArray arr = new JSONArray(); //댓글 목록
		
		for (CommentDTO dto : list) {
			
			JSONObject obj = new JSONObject(); //댓글 1개
			
			obj.put("seq", dto.getSeq());
			obj.put("content", dto.getContent());
			obj.put("id", dto.getId());
			obj.put("name", dto.getName());
			obj.put("regdate", dto.getRegdate());
			
			arr.add(obj);			
		}
		
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.print(arr);
		writer.close();
		
	}

}













