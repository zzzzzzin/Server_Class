package com.test.todo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.todo.model.TodoDAO;
import com.test.todo.model.TodoDTO;

public class Main extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. DB 작업 > select
		//2. 결과 > 전달 > 출력(JSP)
		
		TodoDAO dao = new TodoDAO();
		
		ArrayList<TodoDTO> nlist = dao.list("n");
		ArrayList<TodoDTO> ylist = dao.list("y");
		
//		for (TodoDTO dto : list) {
//			
//			//할일 1개씩 > 완료 유무?
//			if (dto.getState().equals("y")) {
//				dto.setChecked("class='checked'");
//			}
//			
//		}
		
		req.setAttribute("nlist", nlist);
		req.setAttribute("ylist", ylist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}




