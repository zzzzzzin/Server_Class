package com.test.toy.board;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import com.test.toy.board.model.BoardDTO;
import com.test.toy.board.model.CommentDTO;
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
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		String search = req.getParameter("search");
		String page = req.getParameter("page");
		
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
		
		
		//검색 + 내용 > 검색어 강조하기
		//- 오늘은 날씨가 좋습니다.
		//- 오늘은 <span style='background:gold;color:tomato;'>날씨</span>가 좋습니다.
		if (search != null && search.equals("y") && column.equals("content")) {
			content = content.replace(word, "<span style='background:yellow;color:red;'>" + word + "</span>");
		}
		
		
		dto.setContent(content);
		
		
		String subject = dto.getSubject();
		
		//제목 > 태그 > 이스케이프
		subject = subject.replace(">", "&gt;").replace("<", "&lt;");
		
		dto.setSubject(subject);
		
		//댓글 목록 가져오기
		ArrayList<CommentDTO> clist = dao.listComment(seq);
		
		System.out.println(clist.size());
		
		//첨부 파일 > 정보 가져오기
		if(dto.getAttach() != null
				&& (
						dto.getAttach().toLowerCase().endsWith(".jpg")
						|| dto.getAttach().toLowerCase().endsWith(".jpeg")
						|| dto.getAttach().toLowerCase().endsWith(".gif")
						|| dto.getAttach().toLowerCase().endsWith(".png"))) {
			
			//이미지 > 메타 정보 취득
			BufferedImage img 
				= ImageIO.read(new File(req.getRealPath("/asset/place") + "/" + dto.getAttach()));
			
			//System.out.println(img.getWidth());
			//System.out.println(img.getHeight());
			
			//GPS
			try {
				
				Metadata metadata 
					= ImageMetadataReader.readMetadata(new File(req.getRealPath("/asset/place") + "/" + dto.getAttach()));
				
				GpsDirectory gps
					= metadata.getFirstDirectoryOfType(GpsDirectory.class);
				
				if (gps.containsTag(GpsDirectory.TAG_LATITUDE)
						&& gps.containsTag(GpsDirectory.TAG_LONGITUDE)) {
					
					req.setAttribute("lat", gps.getGeoLocation().getLatitude());
					req.setAttribute("lng", gps.getGeoLocation().getLongitude());
				}
				
			} catch (Exception e) {
				System.out.println("View.doGet");
				e.printStackTrace();
				// TODO: handle exception
			}
			
		}
		
		req.setAttribute("dto", dto);
		req.setAttribute("column", column);
		req.setAttribute("word", word);
		req.setAttribute("page", page);
		req.setAttribute("clist", clist);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
		dispatcher.forward(req, resp);

	}

}

