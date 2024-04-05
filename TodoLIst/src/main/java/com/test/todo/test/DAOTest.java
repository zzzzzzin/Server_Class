package com.test.todo.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.test.todo.model.TodoDAO;
import com.test.todo.model.TodoDTO;

class DAOTest {
	
	//주의: 단위테스트는 동시에 작동 
	// => 작동 안 할 테스트에 방지하려면 주석 or 상단에 @Ignore 작성
	
	@Disabled
	@Test
	void testAddTodo() {
		//test'기능명'

		TodoDAO dao = new TodoDAO();
		int result = dao.add("또 할일입니다.");
		
		//assert > 단정 메서드 (테스트 케이스의 수행 결과를 판별) 
		assertEquals(1, result); //실패
	}
	
	@Disabled
	@Test
	void testListTodo() {
		TodoDAO dao = new TodoDAO();
		
		ArrayList<TodoDTO> list = dao.list();
		
		assertNotNull(list); 
	}
	
	@Test
	void testCheckState() {
		TodoDAO dao = new TodoDAO();
		
		int result = dao.checkTodo("1");
		
		assertEquals(1, result);
	}

}







