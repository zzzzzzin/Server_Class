package com.test.todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.todo.model.TodoDAO;

public class CheckOk extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 데이터 가져오기
		//2. DB 작업 > update
		//3. 피드백(JSP)
		
		String seq = req.getParameter("seq");
		
		TodoDAO dao = new TodoDAO();
		
		int result = dao.checkTodo(seq);
		
		req.setAttribute("result", result);
		
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/WEB-INF/views/checkok.jsp");
		dispatcher.forward(req, resp);
		
	}
}
