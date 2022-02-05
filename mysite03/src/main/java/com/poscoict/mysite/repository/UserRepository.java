package com.poscoict.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.exception.UserRepositoryException;
import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.vo.UserVo;

@Repository
public class UserRepository {
	//	 주입
	
	/*
	 * @Autowired private DataSource dataSource;
	 */
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(UserVo vo){
		int count = sqlSession.insert("user.insert",vo);
		return count == 1;
	}
		
	
	public UserVo findByEmailAndPassword(String email, String password)  throws UserRepositoryException{
		Map<String, String> map = new HashMap<>();
		map.put("e", email);
		map.put("passwd", password);
		return sqlSession.selectOne("user.findByEmailAndPassword", map);
	}
	
	public UserVo fidByNo(Long no) {
		return sqlSession.selectOne("user.findByNo", no);
	}


	public int updateByNo(UserVo vo) {
		return sqlSession.update("user.update", vo);
	}


	public String findrole(Long no) {
		return sqlSession.selectOne("user.findrole", no);
	}
}
