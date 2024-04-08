package com.test.todo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.util.DBUtil;

public class TodoDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public TodoDAO() {
		this.conn = DBUtil.open();
	}
	
	//할일 등록하기
	public int add(String todo) {
		
		try {
			
			String sql = "insert into tblTodo (seq, todo, state, regdate) values (seqTodo.nextVal, ?, default, default)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, todo);
			
			return pstat.executeUpdate();			
			
		} catch (Exception e) {
			System.out.println("TodoDAO.add");
			e.printStackTrace();
		}
		
		return 0;		
	}
	
	//목록 보기
	public ArrayList<TodoDTO> list(String state) {
		
		try {
			
			String sql = String.format("select * from tblTodo where state = '%s' order by seq desc", state);
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<TodoDTO> list = new ArrayList<TodoDTO>();
			
			while (rs.next()) {
				//레코드 1개 > TodoDTO 1개
				TodoDTO dto = new TodoDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setTodo(rs.getString("todo"));
				dto.setState(rs.getString("state"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("TodoDAO.list");
			e.printStackTrace();
		}
		
		return null;		
	}
	
	
	//할일 체크하기(했다. 안했다.)
	public int checkTodo(String seq) {
		
		try {
			
			//n > y
			//y > n
			String sql = "select state from tblTodo where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			String state = "";
			
			if (rs.next()) {
				state = rs.getString("state");
			}
			
			rs.close();
			pstat.close();
			
			if (state.equals("y")) {
				state = "n";
			} else {
				state = "y";
			}
			
			sql = "update tblTodo set state = ? where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, state);
			pstat.setString(2, seq);
			
			return pstat.executeUpdate();			
			
		} catch (Exception e) {
			System.out.println("TodoDAO.checkTodo");
			e.printStackTrace();
		}
		
		return 0;		
	}

}




















