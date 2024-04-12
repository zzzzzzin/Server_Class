package com.test.place.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.util.DBUtil;

public class PlaceDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public PlaceDAO() {
		this.conn = DBUtil.open();
	}

	public ArrayList<CategoryDTO> clist() {
		
		try {
			
			String sql = "select * from tblCategory order by name asc";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<CategoryDTO> clist = new ArrayList<CategoryDTO>();
			
			while (rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setMarker(rs.getString("marker"));
				
				clist.add(dto);
			}
			
			return clist;
			
		} catch (Exception e) {
			System.out.println("PlaceDAO.clist");
			e.printStackTrace();
		}
		
		return null;
	}

	public int add(PlaceDTO dto) {
		
		try {
			
			String sql = "insert into tblPlace (seq, name, lat, lng, description, address, category) values (seqPlace.nextVal, ?, ?, ?, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getLat());
			pstat.setString(3, dto.getLng());
			pstat.setString(4, dto.getDescription());
			pstat.setString(5, dto.getAddress());
			pstat.setString(6, dto.getCategory());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("PlaceDAO.add");
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<PlaceDTO> plist(HashMap<String, String> map) {
		
		try {
			
			String sql = "select a.*, (select marker from tblCategory where seq = a.category) as marker from tblPlace a where lat >= ? and lat <= ? and lng >= ? and lng <= ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("swLat"));
			pstat.setString(2, map.get("neLat"));
			pstat.setString(3, map.get("swLng"));
			pstat.setString(4, map.get("neLng"));
			
			rs = pstat.executeQuery();
			
			ArrayList<PlaceDTO> plist = new ArrayList<PlaceDTO>();
			
			while (rs.next()) {
				PlaceDTO dto = new PlaceDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setLat(rs.getString("lat"));
				dto.setLng(rs.getString("lng"));
				dto.setDescription(rs.getString("description"));
				dto.setAddress(rs.getString("address"));
				dto.setCategory(rs.getString("category"));
				dto.setMarker(rs.getString("marker"));
				
				plist.add(dto);
			}
			
			return plist;
			
		} catch (Exception e) {
			System.out.println("PlaceDAO.plist");
			e.printStackTrace();
		}
		
		return null;
	}

	public int del(String seq) {

		try {

			String sql = "delete from tblPlace where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,  seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("PlaceDAO.del");
			e.printStackTrace();
		}
		
		return 0;
	}

}













