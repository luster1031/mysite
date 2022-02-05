package com.poscoict.mysite.service;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.SiteRepository;
import com.poscoict.mysite.vo.SiteVo;

@Service
public class SiteService {
	@Autowired
	private SiteRepository siteRepository;
	@Autowired
	private ServletContext servletContext;

	public boolean updateSite(SiteVo siteVo) {
		servletContext.setAttribute("site", siteVo);
		return siteRepository.updateSite(siteVo);
	}

	public SiteVo getContents() {
		return siteRepository.find();
	}
}
