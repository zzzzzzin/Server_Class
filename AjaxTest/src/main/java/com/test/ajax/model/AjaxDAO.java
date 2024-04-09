package com.test.ajax.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.util.DBUtil;

public class AjaxDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public AjaxDAO() {
		this.conn = DBUtil.open();
	}

	public SurveyDTO get(int seq) {
		
		
		try {

			String sql = "select * from tblSurvey where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1,  seq);
			
			rs = pstat.executeQuery();
			
			SurveyDTO dto = new SurveyDTO();
			
			if(rs.next()) {
				dto.setSeq(rs.getString(seq));
				dto.setQuestion(rs.getString("question"));
				dto.setItem1(rs.getString("item1"));
				dto.setItem2(rs.getString("item2"));
				dto.setItem3(rs.getString("item3"));
				dto.setItem4(rs.getString("item4"));
				dto.setCnt1(rs.getInt("cnt1"));
				dto.setCnt2(rs.getInt("cnt2"));
				dto.setCnt3(rs.getInt("cnt3"));
				dto.setCnt4(rs.getInt("cnt4"));
				
				return dto;
			}
			
			
			
		} catch (Exception e) {
			System.out.println("AjaxDAO.get");
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		return null;
	}

	public int check(String id) {

		try {
			
			String sql = "select count(*) as cnt from tblUser where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			System.out.println("AjaxDAO.check");
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return 0;
	}
	
	

}










