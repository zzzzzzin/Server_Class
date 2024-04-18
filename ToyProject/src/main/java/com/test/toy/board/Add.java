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
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.toy.board.model.BoardDTO;
import com.test.toy.board.repository.BoardDAO;
import com.test.util.OutputUtil;

@WebServlet("/board/add.do")
public class Add extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//새글 쓰기? 답변글 쓰기?
		String reply = req.getParameter("reply");
		String thread = req.getParameter("thread");
		String depth = req.getParameter("depth");
		
		req.setAttribute("reply", reply);
		req.setAttribute("thread", thread);
		req.setAttribute("depth", depth);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/add.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		MultipartRequest multi = new MultipartRequest(
									req,
									req.getRealPath("/asset/place"),
									1024 * 1024 * 30,
									"UTF-8",
									new DefaultFileRenamePolicy()
								);
		
		System.out.println(req.getRealPath("/toy/asset/place"));
		
		
		//1. 데이터 가져오기(subject, content)
		//2. DB 작업 > insert
		//3. 결과
		
		//req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		
		
		String subject = multi.getParameter("subject");
		String content = multi.getParameter("content");
		String reply = multi.getParameter("reply");
		String tag = multi.getParameter("tag");
		String secret = multi.getParameter("secret");
		
		

		BoardDAO dao = new BoardDAO();
		
		
		int thread = -1;
		int depth = -1;
		
		if (reply.equals("n")) {
			//새글 쓰기
			//a. 현존하는 모든 게시물 중에서 가장 큰 thread 값을 찾아서 그 값에 +1000을 한 값을 새글의 thread 값으로 넣는다.
			thread = dao.getMaxThread() + 1000;
			
			//b. 새글의 depth 값은 0을 넣는다.
			depth = 0;			
			
		} else {
			//답변글 쓰기
			//a. 현존하는 모든 게시물의 thread 값을 대상으로, 현재 작성 중인 답변글의 부모글 thread 값보다 작고, 이전 새글의 thread 값보다 큰 thread를 모두 찾아서 -1 을 한다.
			int parentThread = Integer.parseInt(multi.getParameter("thread"));
			int parentDepth = Integer.parseInt(multi.getParameter("depth"));
			int previousThread = (int)Math.floor((parentThread - 1) / 1000) * 1000;
			
			HashMap<String,Integer> map = new HashMap<String,Integer>();
			map.put("parentThread", parentThread);
			map.put("previousThread", previousThread);
			
			dao.updateThread(map);
			
			
			//b. 답변글의 thread 값은 부모글의 thread - 1을 넣는다.
			thread = parentThread - 1;
			
			//c. 답변글의 depth 값은 부모글의 depth + 1을 넣는다.
			depth = parentDepth + 1;
			
		}
		
		
		BoardDTO dto = new BoardDTO();
		
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setId(id);
		dto.setThread(thread);
		dto.setDepth(depth);
		
		dto.setAttach(multi.getFilesystemName("attach"));
		
		dto.setSecret(secret != null ? secret : "0");
		
		
		
		int result = dao.add(dto);
		String bseq = dao.getBseq();
		
		
		
		//해시 태그 작업
		if (tag != null && !tag.equals("") && !tag.equals("[]")) {

			try {
				
				//[{"value":"자바"},{"value":"코딩"},{"value":"게시판"}]
				//System.out.println(tag);
				
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
					System.out.println("hseq: " + hseq);
					
					//관계 추가
					HashMap<String,String> map = new HashMap<String,String>();
					map.put("bseq", bseq);
					map.put("hseq", hseq);
					
					dao.addTagging(map);
					
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
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













