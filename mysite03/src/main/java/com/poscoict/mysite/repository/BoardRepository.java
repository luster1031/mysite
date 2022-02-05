package com.poscoict.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> findAll(String limit, String input, String input2) {
		Map<String, String> map = new HashMap<>();
		map.put("limit", limit);
		map.put("input", input);
		map.put("input2", input2);
		System.out.println("[map] : "+map.toString());
		return sqlSession.selectList("board.findAll",map);
	}


	public int insert(BoardVo vo) {
		
		int g_no = maxGroupno();
		
		vo.setGroupNo(g_no);
		return sqlSession.insert("board.insert",vo);
	}

	public int maxGroupno() {
		if(sqlSession.selectOne("board.maxGroupno")==null) {
			return 1;
		}else {
			return sqlSession.selectOne("board.maxGroupno");
		}
	}

	public int delete(BoardVo vo) {
		return sqlSession.delete("board.delete", vo);
	}

	public int setHit(Long no) {
		return sqlSession.update("board.updatehit", no);
	}

	public BoardVo view(Long no) {
		return sqlSession.selectOne("board.view", no);
	}

	public BoardVo updateSerach(Long no, Long userno) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("userNo", userno);
		System.out.println(map.toString());
		BoardVo boardvo = sqlSession.selectOne("board.updateSerach",map);
		System.out.println(boardvo.toString());
		return boardvo;
	}

	public int update(BoardVo vo) {
		return sqlSession.update("board.update", vo);
	}

	public int getTotalCount(String keyword) {
		int count = sqlSession.selectOne("board.getTotalCount", keyword);
		System.out.println(count);
		return count;
	}

	public BoardVo findByNO(Long no) {
		System.out.println("[findByNo] : "+ no);
		return sqlSession.selectOne("board.findByNO",no);
	}

	public int comment(BoardVo vo) {
		updateONO(vo);
		System.out.println("update : "+ vo.toString());
		return sqlSession.insert("board.comment",vo);
	}

	public int updateONO(BoardVo vo) {
		return sqlSession.update("board.updateONO", vo);
	}

	public int deletePost(BoardVo vo) {
		System.out.println("deletepost " + vo.toString());
		int count =  sqlSession.update("board.deletePost",vo);
		return count;
	}

}