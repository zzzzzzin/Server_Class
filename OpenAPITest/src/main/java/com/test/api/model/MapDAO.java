package com.test.api.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.util.DBUtil;

public class MapDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public MapDAO() {
		this.conn = DBUtil.open();
	}

	public ArrayList<MarkerDTO> list() {
		
		try {
			
			//ResultSet > (복사) > ArrayList<MarkerDTO> 
			ArrayList<MarkerDTO> list = new ArrayList<MarkerDTO>();
			
			String sql = "select * from tblMarker";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				MarkerDTO dto = new MarkerDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setLat(rs.getString("lat"));
				dto.setLng(rs.getString("lng"));
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("MapDAO.list");
			e.printStackTrace();
		}
		
		return null;
	}

	public void add(MarkerDTO dto) {
		
		try {
			
			String sql = "insert into tblMarker values (seqMarker.nextVal, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getLat());
			pstat.setString(2, dto.getLng());
			
			pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MapDAO.add");
			e.printStackTrace();
		}
		
	}
	
}











