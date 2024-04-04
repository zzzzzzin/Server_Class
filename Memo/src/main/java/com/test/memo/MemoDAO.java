package com.test.memo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.util.DBUtil;

public class MemoDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public MemoDAO() {
		conn = DBUtil.open();
	}
	
	public int add(MemoDTO dto) {
		
		try {
			
			String sql = "insert into tblMemo (seq, name, pw, memo, regdate) values (seqMemo.nextVal, ?, ?, ?, default)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getMemo());

			//insert 성공(1) 실패(0)
			return pstat.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	public ArrayList<MemoDTO> list() {
		
		try {
			
			String sql = "select * from tblMemo order by seq desc";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<MemoDTO> list = new ArrayList<MemoDTO>();
			
			//rs(원본) > list(복사본)
			while (rs.next()) {
				//레코드 1개 > MemoDTO 1개
				MemoDTO dto = new MemoDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setMemo(rs.getString("memo"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);				
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;		
	}
	
	public MemoDTO get(String seq) {
		
		try {
			
			String sql = "select * from tblMemo where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				//레코드 1개 > MemoDTO 1개
				MemoDTO dto = new MemoDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setMemo(rs.getString("memo"));
				dto.setRegdate(rs.getString("regdate"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;		
	}
	
	public int edit(MemoDTO dto) {
		
		try {
			
			String sql = "update tblMemo set memo = ? where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getMemo());
			pstat.setString(2, dto.getSeq());

			//insert 성공(1) 실패(0)
			return pstat.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public boolean check(MemoDTO dto) {
		
		try {
			
			String sql = "select count(*) as cnt from tblMemo where seq = ? and pw = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSeq());
			pstat.setString(2, dto.getPw());
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("cnt") == 1 ? true : false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;		
	}
	
	public int del(String seq) {
		
		try {
			
			String sql = "delete from tblMemo where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}








