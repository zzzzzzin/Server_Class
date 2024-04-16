package com.test.toy.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.toy.board.model.BoardDTO;
import com.test.toy.board.model.CommentDTO;
import com.test.util.DBUtil;

public class BoardDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public BoardDAO() {
		this.conn = DBUtil.open("localhost", "toy", "java1234");
	}

	public int add(BoardDTO dto) {
		
		//queryParamNoReturn
		try {

			String sql = "insert into tblBoard (seq, subject, content, id, regdate, readcount) values (seqBoard.nextVal, ?, ?, ?, default, default)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSubject());
			pstat.setString(2, dto.getContent());
			pstat.setString(3, dto.getId());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.add");
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<BoardDTO> list(HashMap<String, String> map) {
		
		//queryNoParamListReturn
		try {
			
			//목록보기 > select * from vwBoard
			//검색하기 > select * from vwBoard where 조건
			String where = "";
			
			if (map.get("search").equals("y")) {
				
				//where subject like '%강아지%'
				//where content like '%강아지%'
				//where name like '%강아지%'
				
				where = String.format("where %s like '%%%s%%'"
										, map.get("column")
										, map.get("word"));
			}
			
			String sql = String.format("select * from (select a.*, rownum as rnum from vwBoard a %s) where rnum between %s and %s"
										, where
										, map.get("begin")
										, map.get("end"));
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
			
			while (rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSubject(rs.getString("subject"));
				dto.setName(rs.getString("name"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcount(rs.getString("readcount"));
				dto.setId(rs.getString("id"));
				
				dto.setIsnew(rs.getDouble("isnew"));
				
				list.add(dto);			
			}	
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public BoardDTO get(String seq) {
		
		//queryParamDTOReturn
		try {
			
			String sql = "select a.*, (select name from tblUser where id = a.id) as name from tblBoard a where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("id"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcount(rs.getString("readcount"));
				
				return dto;				
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public void updateReadcount(String seq) {
		
		//queryParamNoReturn
		try {

			String sql = "update tblBoard set readcount = readcount + 1 where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.updateReadcount");
			e.printStackTrace();
		}
		
	}

	public int edit(BoardDTO dto) {
		
		//queryParamNoReturn
		try {

			String sql = "update tblBoard set subject = ?, content = ? where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSubject());
			pstat.setString(2, dto.getContent());
			pstat.setString(3, dto.getSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.edit");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int del(String seq) {
		
		//queryParamNoReturn
		try {

			String sql = "delete from tblBoard where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.del");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int getTotalCount(HashMap<String, String> map) {
		
		//queryParamTokenReturn
		try {
			
			String where = "";
			
			if (map.get("search").equals("y")) {
				
				where = String.format("where %s like '%%%s%%'"
										, map.get("column")
										, map.get("word"));
			}

			String sql = String.format("select count(*) as cnt from vwBoard %s", where);

			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int addComment(CommentDTO dto) {

		try {

			String sql = "insert into tblComment (seq, content, id, regdate, bseq) values (seqComment.nextVal, ?, ?, default, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getContent());
			pstat.setString(2, dto.getId());
			pstat.setString(3, dto.getBseq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.addComment");
			e.printStackTrace();
		}
		
		return 0;
	}

}








