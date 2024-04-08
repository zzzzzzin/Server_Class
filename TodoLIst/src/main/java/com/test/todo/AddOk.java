package com.test.todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.todo.model.TodoDAO;

public class AddOk extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 데이터 가져오기
		//2. DB 작업 > insert
		//3. 피드백
		
		//POST + 한글
		req.setCharacterEncoding("UTF-8");
		
		String todo = req.getParameter("todo");
		
		TodoDAO dao = new TodoDAO();
		
		int result = dao.add(todo);

		req.setAttribute("result", result);
		
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/WEB-INF/views/addok.jsp");
		dispatcher.forward(req, resp);
		
	}
}
