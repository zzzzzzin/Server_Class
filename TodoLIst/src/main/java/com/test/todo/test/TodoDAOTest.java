package com.test.todo.test;

import com.test.todo.model.TodoDAO;

//단위테스트용 클래스명: '테스트대상클래스명'Test 
public class TodoDAOTest {
	public static void main(String[] args) {
		//1이 출력되면 add(String todo)의 기능이 정상 작동
		
		TodoDAO dao = new TodoDAO();
		
		int result = dao.add("할일입니다.");
		
		System.out.println(result);
		
	}
}




