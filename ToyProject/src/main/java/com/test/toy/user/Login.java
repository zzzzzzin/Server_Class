package com.test.toy.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.toy.user.model.UserDTO;
import com.test.toy.user.repository.UserDAO;
import com.test.util.OutputUtil;

@WebServlet("/user/login.do")
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Login.java 역할

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/login.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//LoginOk.java 역할
		//1. 데이터 가져오기(id, pw)
		//2. DB 작업 > select
		//3. 결과 > 인증 티켓
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		UserDAO dao = new UserDAO();
		
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setPw(pw);
		
		//int result = dao.login(dto);		//결과가 1 또는 0
		UserDTO result = dao.login(dto);	//결과가 dto 또는 null
		
		if (result != null) {
			//인증 처리
			
			HttpSession session = req.getSession();
			
			session.setAttribute("id", id);	//인증 티켓
			session.setAttribute("name", result.getName());
			session.setAttribute("lv", result.getLv());
			
			resp.sendRedirect("/toy/index.do");
			
		} else {
			//실패 처리
			
			resp.setCharacterEncoding("UTF-8");
			
			PrintWriter writer = resp.getWriter();
			writer.print(OutputUtil.redirect("로그인을 실패했습니다."));
			writer.close();
			
		}
		
		
	}
}
