package com.poscoict.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.SiteVo;

@Repository
public class SiteRepository {
	@Autowired
	private SqlSession sqlSession;

	public boolean updateSite(SiteVo siteVo) {
		return sqlSession.update("site.update", siteVo)==1;
	}

	public SiteVo find() {
		SiteVo sitevo = sqlSession.selectOne("site.find");
		System.out.println("find - sitevo : \n"+sitevo.toString());
		return sitevo;
	}
	
}
