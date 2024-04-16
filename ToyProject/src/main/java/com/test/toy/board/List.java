package com.test.toy.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
		
		
		//호출 상황
		//1. 목록보기
		//	- list.do
		//2. 검색하기
		//	- list.do?column=subject&word=검색어
		
		// - list.do?begin=11&end=20
		// - list.do?page=2
		// - list.do
		
		
		//페이징
		String page = req.getParameter("page");
		
		int nowPage = 0;	//현재 페이지 번호
		int totalCount = 0;	//총 게시물 수
		int pageSize = 10;	//한 페이지에서 출력할 게시물 수
		int totalPage = 0;	//총 페이지 수
		int begin = 0;		//페이징 시작 위치
		int end = 0;		//페이지 끝 위치
		int n = 0;			
		int loop = 0;
		int blockSize = 10;	//페이지 개수
		
		if (page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		
		//board.do?page=1 > where rnum between 1 and 10
		//board.do?page=2 > where rnum between 11 and 20
		//board.do?page=3 > where rnum between 21 and 30
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		
		
		
		
		
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		String search = "n"; //목록보기(n), 검색하기(y)
		
		if ((column == null && word == null)
				|| (word.equals(""))) {
			search = "n";
		} else {
			search = "y";
		}
			
		//System.out.println("search: " + search);
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		if (column == null) column = "";
		if (word == null) word = "";
		
		map.put("search", search); //n, y
		map.put("column", column);
		map.put("word", word);		
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		
		
		
		HttpSession session = req.getSession();
		
		//조회수 관련
		session.setAttribute("read", "n");
		
		
		//1. DB 작업 > select
		//2. 결과 > 출력
		BoardDAO dao = new BoardDAO();
		
		ArrayList<BoardDTO> list = dao.list(map);
		
		
		
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
		
		
		
		//총 게시물? 263
		//총 페이지수? 263 / 10 = 26.3 > 27
		totalCount = dao.getTotalCount(map);
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
		
		
		
		//페이지 바 작업
		StringBuilder sb = new StringBuilder();
		
//		for (int i=1; i<=totalPage; i++) {
//			sb.append(String.format(" <a href='/toy/board/list.do?page=%d'>%d</a> ", i, i));
//		}
		
		//board.do?page=1
		//[] 1 2 3 4 5 6 7 8 9 10 []
		
		//board.do?page=2
		//[] 1 2 3 4 5 6 7 8 9 10 []
		
		//board.do?page=10
		//[] 1 2 3 4 5 6 7 8 9 10 []
		
		//board.do?page=11
		//[] 11 12 13 14 15 16 17 18 19 20 []
		
		loop = 1; //루프 변수(10바퀴)
		n = ((nowPage - 1) / blockSize) * blockSize + 1; //페이지 번호 역할
		
		
		//이전 10페이지
		if (n == 1) {
			sb.append(String.format(" <a href='#!'>[이전 %d페이지]</a> ", blockSize));
		} else {
			sb.append(String.format(" <a href='/toy/board/list.do?page=%d&column=%s&word=%s'>[이전 %d페이지]</a> ", n - 1, column, word, blockSize));
		}
		
		
		
		while (!(loop > blockSize || n > totalPage)) {
			
			if (n == nowPage) {
				sb.append(String.format(" <a href='#!' style='color: tomato;'>%d</a> ", n));
			} else {
				sb.append(String.format(" <a href='/toy/board/list.do?page=%d&column=%s&word=%s'>%d</a> ", n, column, word, n));
			}
			
			loop++;
			n++;
		}
		
		
		//다음 10페이지
		if (n >= totalPage) {
			sb.append(String.format(" <a href='#!'>[다음 %d페이지]</a> ", blockSize));
		} else {
			sb.append(String.format(" <a href='/toy/board/list.do?page=%d&column=%s&word=%s'>[다음 %d페이지]</a> ", n, column, word, blockSize));
		}
		
		
		
		req.setAttribute("list", list);
		req.setAttribute("map", map);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pagebar", sb.toString());

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		dispatcher.forward(req, resp);

	}

}

