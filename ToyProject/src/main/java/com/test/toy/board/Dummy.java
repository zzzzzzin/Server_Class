package com.test.toy.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;

import com.test.toy.board.model.BoardDTO;
import com.test.toy.board.repository.BoardDAO;
import com.test.toy.user.repository.UserDAO;
import com.test.util.DBUtil;

public class Dummy {
	
	public static void main(String[] args) throws Exception {
						
		//m1();
		//m2();
		//m3();
		m4();
		
	}

	private static void m4() throws Exception {
		
		
		//3. 댓글 > 게시물 일부 > 2~5건
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		BoardDAO dao = new BoardDAO();
		
		conn = DBUtil.open("localhost", "toy", "java1234");
		String sql = "select seq, regdate from tblBoard";
		pstat = conn.prepareStatement(sql);
		rs = pstat.executeQuery();
		
		Random rnd = new Random();
		
		
		
		sql = "insert into tblComment (seq, content, id, regdate, bseq) values (seqComment.nextVal, ?, ?, to_date(?, 'yyyy-mm-dd hh24:mi:ss'), ?)";

		PreparedStatement pstat2 = conn.prepareStatement(sql);
		
		
		
		while (rs.next()) {
			
			String seq = rs.getString("seq");
			
					
			if (rnd.nextBoolean()) {
				
				int count = rnd.nextInt(4) + 2;
				
				for (int i=0; i<count; i++) {

					//댓글 추가하기
					pstat2.setString(1, "댓글입니다.");
					pstat2.setString(2, "hong");
					pstat2.setString(3, rs.getString("regdate").replace(".0", ""));
					pstat2.setString(4, seq);

					pstat2.executeUpdate();
				}
			}
			System.out.println("complete");			
		}
		
		
		
	}

	private static void m3() throws Exception {
		
		//1. 1명 > hong
		//2. 3개월치
		//	- 일주일 > 3~4번 로그인
		//	- 로그인 > 글 2~5번 작성
		

		// 주어 배열 생성
        String[] subjects = {
            "그녀는", "그는", "나는", "너는", "우리는", "그들은", "이", "저는", "여러분은", "이들은",
            "저희는", "누구를", "무엇을", "어디에", "언제를", "왜를", "어떻게를", "모두를", "아무것도", "아무나",
            "아무", "누가를", "무슨을", "얼마나를", "얼마만큼을", "모든 것을", "어느 것을", "한 것을", "한때를", "몇몇을"
        };

        // 서술어 배열 생성
        String[] verbs = {
            "사랑한다", "생각한다", "좋아한다", "가고싶다", "이해한다", "도와준다", "찾아낸다", "기억한다", "알려준다", "보낸다",
            "응원한다", "싫어한다", "걸어간다", "달려간다", "쓰려고한다", "놀러간다", "놀러온다", "먹는다", "술마신다", "웃는다",
            "울다", "놀란다", "떠난다", "오는", "간다", "줍는다", "버린다", "내버려둔다", "숨기다", "보이다"
        };

        // 목적어 배열 생성
        String[] objects = {
            "사람을", "꽃을", "나무를", "바다를", "하늘을", "별을", "햇빛을", "숲을", "강을", "산을",
            "꿈을", "희망을", "미래를", "과거를", "현재를", "지금을", "시간을", "기억을", "우정을", "가을을",
            "겨울을", "봄을", "여름을", "행복을", "슬픔을", "웃음을", "미소를", "관계를", "가족을", "친구를",
            "사진을", "모래를", "바람을", "빗물을", "눈을", "달을", "별빛을", "책을", "음악을",
            "영화를", "사람을", "동물을", "도시를", "마을을", "집을", "방을", "창문을", "문을", "바닷가를"
        };
		
		
		
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		
		UserDAO dao = new UserDAO();
		BoardDAO bdao = new BoardDAO();
		
		
		
		Calendar c1 = Calendar.getInstance();
		
		c1.add(Calendar.MONTH, -3);
		
		//System.out.printf("%tF", c1);
		
		Random rnd = new Random();
		int[] seed = { 1, 1, 1, 1, 1, 1, 1, 0, 0, 0 };
		int[] seed2 = { 2, 3, 4, 5 };
		
		int thread = 1000;
		int depth = 0;
				
		while (!String.format("%tF", c1).equals("2024-04-19")) {
			
			int result = seed[rnd.nextInt(seed.length)];
			
			if (result == 1) {
				
				//로그인 기록 추가
				
				dao.addLog("hong", String.format("%tF", c1));
				
				BoardDTO dto = new BoardDTO();
				dto.setContent("내용");
				dto.setId("hong");
				dto.setDepth(depth);
				dto.setSecret("0");
				
				
				Calendar copy = (Calendar)c1.clone();
				
				//글 작성하기 2~5번
				for (int i=0; i<seed2[rnd.nextInt(seed2.length)]; i++) {
					
					String subject 
						= subjects[rnd.nextInt(subjects.length)] + " " 
						+ objects[rnd.nextInt(objects.length)] + " "
						+ verbs[rnd.nextInt(verbs.length)];
					
					dto.setSubject(subject);
					dto.setThread(thread);
					
					bdao.add(dto, String.format("%tF %tT", copy, copy));
					
					thread += 1000;
					copy.add(Calendar.HOUR, i * 2);
					
				}
				
			}
			
			c1.add(Calendar.DATE, 1);
		}
		
		System.out.println("complete");
		
	}

	private static void m2() throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		
		conn = DBUtil.open("localhost", "toy", "java1234");
		
		String sql = "insert into tblComment (seq, content, id, regdate, bseq) values (seqComment.nextVal, ?, 'hong', sysdate - ?, 281)";
		
		pstat = conn.prepareStatement(sql);
		
		for (int i=0; i<45; i++) {
			pstat.setString(1, "댓글 페이징 처리.." + i);
			pstat.setInt(2, 45 - i);
			pstat.executeUpdate();
			System.out.println(i);
		}
		
		pstat.close();
		conn.close();
	}

	private static void m1() throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		
		conn = DBUtil.open("localhost", "toy", "java1234");
		
		String sql = "insert into tblBoard values (seqBoard.nextVal, ?, '내용', 'hong', default, default)";
		
		pstat = conn.prepareStatement(sql);
		
		for (int i=0; i<250; i++) {
			pstat.setString(1, "게시판 페이징 처리.." + i);
			pstat.executeUpdate();
			System.out.println(i);
		}
		
		pstat.close();
		conn.close();
	}

}









