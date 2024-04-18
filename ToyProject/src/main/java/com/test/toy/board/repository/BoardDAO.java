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

			String sql = "insert into tblBoard (seq, subject, content, id, regdate, readcount, thread, depth, attach, secret) values (seqBoard.nextVal, ?, ?, ?, default, default, ?, ?, ?, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSubject());
			pstat.setString(2, dto.getContent());
			pstat.setString(3, dto.getId());
			pstat.setInt(4, dto.getThread());
			pstat.setInt(5, dto.getDepth());
			pstat.setString(6, dto.getAttach());
			pstat.setString(7, dto.getSecret());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.add");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int add(BoardDTO dto, String regdate) {
		
		//queryParamNoReturn
		try {

			String sql = "insert into tblBoard (seq, subject, content, id, readcount, thread, depth, attach, secret, regdate) values (seqBoard.nextVal, ?, ?, ?, default, ?, ?, ?, ?, to_date(?, 'yyyy-mm-dd hh24:mi:ss'))";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSubject());
			pstat.setString(2, dto.getContent());
			pstat.setString(3, dto.getId());
			pstat.setInt(4, dto.getThread());
			pstat.setInt(5, dto.getDepth());
			pstat.setString(6, dto.getAttach());
			pstat.setString(7, dto.getSecret());
			pstat.setString(8, regdate);

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
			
			String sql = ""; 
			
			if (map.get("tag") == null) {
				sql = String.format("select * from (select a.*, rownum as rnum from vwBoard a %s) where rnum between %s and %s"
										, where
										, map.get("begin")
										, map.get("end"));
			} else {
				sql = String.format("select * from (select a.*, rownum as rnum from vwBoard a %s) b inner join tblTagging t on b.seq = t.bseq inner join tblHashtag h on h.seq = t.hseq where rnum between %s and %s and h.tag = '%s'", where
			   , map.get("begin")
			   , map.get("end")
			   , map.get("tag"));
			}
			
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
				
				dto.setCommentcount(rs.getString("commentcount"));
				
				dto.setDepth(rs.getInt("depth"));
				
				dto.setSecret(rs.getString("secret"));
				
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
				
				dto.setThread(rs.getInt("thread"));
				dto.setDepth(rs.getInt("depth"));
				
				dto.setAttach(rs.getString("attach"));
				
				
				
				//해시 태그
				sql = "select h.tag from tblBoard b"
						+ "    inner join tblTagging t"
						+ "        on b.seq = t.bseq"
						+ "            inner join tblHashtag h"
						+ "                on h.seq = t.hseq"
						+ "                    where b.seq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				rs = pstat.executeQuery();
				
				ArrayList<String> tlist = new ArrayList<String>();
				
				while (rs.next()) {
					tlist.add(rs.getString("tag"));
				}
				
				dto.setTag(tlist);
				
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

			String sql = "";
			
			sql = "delete from tblComment where bseq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			pstat.executeUpdate();
			
			sql = "delete from tblTagging where bseq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			pstat.executeUpdate();
			
			sql = "delete from tblBoard where seq = ?";
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
		
		//queryParamNoReturn
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

	public ArrayList<CommentDTO> listComment(String seq) {
		
		//queryParamListReturn
		try {
			
			String sql = "select * from (select b.*, rownum as rnum from (select a.*, (select name from tblUser where id = a.id) as name from tblComment a where bseq = ? order by seq desc) b) where rnum between ? and ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			pstat.setString(2, "1");
			pstat.setString(3, "10");
			
			rs = pstat.executeQuery();
			
			ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
			
			while (rs.next()) {
				
				CommentDTO dto = new CommentDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setContent(rs.getString("content"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);		
			}	
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public CommentDTO getComment() {
		
		//queryNoParamDTOReturn
		try {
			
			String sql = "select a.*, (select name from tblUser where id = a.id) as name from tblComment a where seq = (select max(seq) from tblComment)";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				
				CommentDTO dto = new CommentDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setContent(rs.getString("content"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setRegdate(rs.getString("regdate"));
				
				return dto;				
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int delComment(String seq) {
		
		//queryParamNoReturn
		try {

			String sql = "delete from tblComment where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.delComment");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int editComment(CommentDTO dto) {
		
		//queryParamNoReturn
		try {

			String sql = "update tblComment set content = ? where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getContent());
			pstat.setString(2, dto.getSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.editComment");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int getMaxThread() {
		
		//queryNoParamTokenReturn
		try {

			String sql = "select nvl(max(thread), 0) as thread from tblBoard";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				return rs.getInt("thread");
			}

			return stat.executeUpdate(sql);

		} catch (Exception e) {
			System.out.println("BoardDAO.getMaxThread");
			e.printStackTrace();
		}
		
		return 0;
	}

	public void updateThread(HashMap<String, Integer> map) {
		
		//queryParamNoReturn
		try {

			String sql = "update tblBoard set thread = thread - 1 where thread > ? and thread < ?";

			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, map.get("previousThread"));
			pstat.setInt(2, map.get("parentThread"));

			pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.updateThread");
			e.printStackTrace();
		}
		
	}

	public ArrayList<CommentDTO> listMoreComment(HashMap<String, String> map) {
		
		//queryParamListReturn
		try {
			
			String sql = "select * from (select b.*, rownum as rnum from (select a.*, (select name from tblUser where id = a.id) as name from tblComment a where bseq = ? order by seq desc) b) where rnum between ? and ? + 9";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("bseq"));
			pstat.setString(2, map.get("commentBegin"));
			pstat.setString(3, map.get("commentBegin"));			
			
			rs = pstat.executeQuery();
			
			ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
			
			while (rs.next()) {
				
				CommentDTO dto = new CommentDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setContent(rs.getString("content"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);				
			}	
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void addHashtag(String tagName) {
		
		//queryParamNoReturn
		try {

			String sql = "insert into tblHashtag (seq, tag) values (seqHashtag.nextVal, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, tagName);

			pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.addHashtag");
			e.printStackTrace();
		}
		
	}

	public boolean existHashtag(String tagName) {
		
		//queryParamTokenReturn
		try {

			String sql = "select count(*) as cnt from tblHashtag where tag = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, tagName);

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getInt("cnt") == 1 ? false : true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
	}

	public String getBseq() {
		
		//queryNoParamTokenReturn
		try {

			String sql = "select max(seq) as seq from tblBoard";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("seq");
			}

		} catch (Exception e) {
			System.out.println("BoardDAO.getBseq");
			e.printStackTrace();
		}
		
		return null;
	}

	public String getHseq(String tagName) {
		
		//queryParamTokenReturn
		try {

			String sql = "select seq from tblHashtag where tag = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, tagName);

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getString("seq");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void addTagging(HashMap<String, String> map) {
		
		//queryParamNoReturn
		try {

			String sql = "insert into tblTagging (seq, bseq, hseq) values (seqTagging.nextVal, ?, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("bseq"));
			pstat.setString(2, map.get("hseq"));

			pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.addTagging");
			e.printStackTrace();
		}
		
	}

	public boolean existTagging(HashMap<String, String> map) {
		
		//queryParamTokenReturn
		try {

			String sql = "select count(*) as cnt from tblTagging where bseq = ? and hseq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("bseq"));
			pstat.setString(2, map.get("hseq"));

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getInt("cnt") == 0 ? true : false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public void delTagging(HashMap<String, String> map) {
		
		//queryParamNoReturn
		try {

			String sql = "delete from tblTagging where bseq = ? and hseq = (select seq from tblHashtag where tag = ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("bseq"));
			pstat.setString(2, map.get("tag"));

			pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.delTagging");
			e.printStackTrace();
		}
		
	}

}








