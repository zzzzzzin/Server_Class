package com.test.toy.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.test.toy.board.model.BoardDTO;
import com.test.toy.board.repository.BoardDAO;
import com.test.util.OutputUtil;

@WebServlet("/board/edit.do")
public class Edit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//인증받지 못한 사용자 or 권한이 없는 사용자 > 거부
		if (Auth.check(req, resp)) {
			return;
		}
		

		//1. 데이터 가져오기(seq)
		//2. DB 작업 > select > 수정할 데이터의 원본을 미리 보여주기 위해서
		//3. 결과 > 출력
		
		String seq = req.getParameter("seq");
		
		BoardDAO dao = new BoardDAO();
		
		BoardDTO dto = dao.get(seq);
		
		req.setAttribute("seq", seq);
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/edit.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 데이터 가져오기
		//2. DB 작업 > update
		//3. 결과
		
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		String seq = req.getParameter("seq");
		String tag = req.getParameter("tag");
		String[] removeTag = req.getParameterValues("removeTag");
		
		
		BoardDAO dao = new BoardDAO();
		
		BoardDTO dto = new BoardDTO();
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setSeq(seq);
		
		int result = dao.edit(dto);
				
		
		//해시 태그 수정
		//해시 태그 작업
		if (tag != null && !tag.equals("") && !tag.equals("[]")) {

			try {
				
				//[{"value":"자바"},{"value":"코딩"},{"value":"게시판"},{"value":"여행"}]
				
				JSONParser parser = new JSONParser();
				JSONArray arr = (JSONArray)parser.parse(tag);
				
				for (Object obj : arr) {
					
					JSONObject tagObj = (JSONObject)obj;
					String tagName = (String)tagObj.get("value");
					//System.out.println(tagName);
					
					//해시 태그 추가
					if (dao.existHashtag(tagName)) {
						dao.addHashtag(tagName);
					} 
					
					String hseq = dao.getHseq(tagName);	
					
					
					HashMap<String,String> map = new HashMap<String,String>();
					map.put("bseq", seq);
					map.put("hseq", hseq);
					
					if (dao.existTagging(map)) {
						//관계 추가						
						dao.addTagging(map);
					}
					
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
		}
		
		//해시 태그 삭제
		HashMap<String,String> map = new HashMap<String,String>();
		
		for (String tagName : removeTag) {
			
			map.put("bseq", seq);
			map.put("tag", tagName);
			
			dao.delTagging(map);
			
		}
		
		
		
		if (result == 1) {
			//resp.sendRedirect("/toy/board/list.do");
			resp.sendRedirect("/toy/board/view.do?seq=" + seq);
		} else {
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.print(OutputUtil.redirect("실패했습니다."));
			writer.close();
		}
		
	}

}










