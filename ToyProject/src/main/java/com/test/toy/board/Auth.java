package com.test.toy.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.toy.board.model.BoardDTO;
import com.test.toy.board.repository.BoardDAO;
import com.test.util.OutputUtil;

public class Auth {

	public static boolean check(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		HttpSession session = req.getSession();
		
		//Edit.java의 String seq = req.getParameter("seq");와 동일 > 따로 받지 않아도 seq 사용 가능
		String seq = req.getParameter("seq");
		
		//글쓴이 본인?
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.get(seq);
		
		//id != dto.id && lv != 2
		if (session.getAttribute("id") == null || 
				(!session.getAttribute("id").toString().equals(dto.getId())
				&& !session.getAttribute("lv").toString().equals("2"))) {
			
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.print(OutputUtil.redirect("권한이 없습니다."));
			writer.close();
			
			return true;
		}
			
		
		
		
		
		
		return false;
	}

}
