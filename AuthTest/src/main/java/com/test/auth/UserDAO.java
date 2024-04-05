package com.test.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.util.DBUtil;

public class UserDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public UserDAO() {
		this.conn = DBUtil.open();
	}
	
	public UserDTO check(UserDTO dto) {
		
		try {
			
			String sql = "select * from tblUser"
							+ " where id = ? and pw = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				UserDTO result = new UserDTO();
				
				result.setId(rs.getString("id"));
				result.setName(rs.getString("name"));
				result.setLv(rs.getString("lv"));
				
				return result;				
			}
			
		} catch (Exception e) {
			System.out.println("UserDAO.check");
			e.printStackTrace();
		}
		
		return null;		
	}

}







